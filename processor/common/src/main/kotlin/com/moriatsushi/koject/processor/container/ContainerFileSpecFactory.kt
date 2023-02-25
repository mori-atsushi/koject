package com.moriatsushi.koject.processor.container

import com.moriatsushi.koject.internal.Container
import com.moriatsushi.koject.internal.identifier.Identifier
import com.moriatsushi.koject.processor.code.AnnotationSpecFactory
import com.moriatsushi.koject.processor.code.Names
import com.moriatsushi.koject.processor.code.applyCommon
import com.moriatsushi.koject.processor.error.DependencyResolutionException
import com.moriatsushi.koject.processor.symbol.AllFactoryDeclarations
import com.moriatsushi.koject.processor.symbol.FactoryDeclaration
import com.squareup.kotlinpoet.ANY
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.LambdaTypeName
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.buildCodeBlock
import com.squareup.kotlinpoet.ksp.addOriginatingKSFile

internal class ContainerFileSpecFactory {
    fun create(allFactories: AllFactoryDeclarations): FileSpec {
        val typeSpec = createContainerClass(allFactories)

        return FileSpec.builder(
            Names.containerClassName.packageName,
            Names.containerClassName.simpleName,
        ).apply {
            applyCommon()
            addType(typeSpec)
        }.build()
    }

    private fun createContainerClass(allFactories: AllFactoryDeclarations): TypeSpec {
        val internalAnnotationSpec =
            AnnotationSpecFactory.createInternal()

        return TypeSpec.classBuilder(Names.containerClassName).apply {
            addSuperinterface(Container::class)
            allFactories.all.forEach {
                addProperty(createProviderPropertySpec(allFactories, it))
            }
            addFunction(createGetFunSpec(allFactories))
            addAnnotation(internalAnnotationSpec)

            allFactories.all
                .mapNotNull { it.containingFile }
                .forEach { addOriginatingKSFile(it) }
        }.build()
    }

    private fun createProviderPropertySpec(
        allFactories: AllFactoryDeclarations,
        factoryClass: FactoryDeclaration,
    ): PropertySpec {
        validateDependencies(allFactories, factoryClass)

        val factoryName = factoryClass.asClassName()
        val providerName = Names.providerNameOf(factoryClass.identifier)
        val type = LambdaTypeName.get(returnType = ANY)
        val code = buildCodeBlock {
            add("lazy·{\n")
            indent()
            add("%T(", factoryName)
            if (factoryClass.parameters.isNotEmpty()) {
                add("\n")
                indent()
                factoryClass.parameters.forEach {
                    add("${it.providerName},\n")
                }
                unindent()
            }
            add(")::create\n")
            unindent()
            add("}")
        }

        return PropertySpec.builder(providerName, type).apply {
            addModifiers(KModifier.PRIVATE)
            delegate(code)
        }.build()
    }

    private fun validateDependencies(
        allFactories: AllFactoryDeclarations,
        factoryClass: FactoryDeclaration,
    ) {
        factoryClass.parameters.forEach {
            if (!allFactories.contains(it.identifier)) {
                throw DependencyResolutionException(
                    "${it.identifier} is not provided. " +
                        "It is requested by ${factoryClass.identifier}.",
                )
            }
        }
    }

    private fun createGetFunSpec(
        allFactories: AllFactoryDeclarations,
    ): FunSpec {
        return FunSpec.builder("resolve").apply {
            returns(ANY.copy(nullable = true))
            addModifiers(KModifier.OVERRIDE)
            addParameter("id", Identifier::class)
            beginControlFlow("return when (id) {")
            allFactories.all.forEach {
                val name = Names.providerNameOf(it.identifier)
                addStatement("%T.identifier -> $name()", it.asClassName())
            }
            addStatement("else -> null")
            endControlFlow()
        }.build()
    }
}
