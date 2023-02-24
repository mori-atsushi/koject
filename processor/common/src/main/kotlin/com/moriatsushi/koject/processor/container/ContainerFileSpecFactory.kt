package com.moriatsushi.koject.processor.container

import com.moriatsushi.koject.internal.Container
import com.moriatsushi.koject.internal.identifier.Identifier
import com.moriatsushi.koject.processor.code.AnnotationSpecFactory
import com.moriatsushi.koject.processor.code.Names
import com.moriatsushi.koject.processor.code.applyCommon
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
    fun create(factoryClasses: Sequence<FactoryDeclaration>): FileSpec {
        val typeSpec = createContainerClass(factoryClasses)

        return FileSpec.builder(
            Names.containerClassName.packageName,
            Names.containerClassName.simpleName,
        ).apply {
            applyCommon()
            addType(typeSpec)
        }.build()
    }

    private fun createContainerClass(factoryClasses: Sequence<FactoryDeclaration>): TypeSpec {
        val internalAnnotationSpec =
            AnnotationSpecFactory.createInternal()

        return TypeSpec.classBuilder(Names.containerClassName).apply {
            addSuperinterface(Container::class)
            factoryClasses.forEach {
                addProperty(createProviderPropertySpec(it))
            }
            addFunction(createGetFunSpec(factoryClasses))
            addAnnotation(internalAnnotationSpec)

            factoryClasses
                .mapNotNull { it.containingFile }
                .forEach { addOriginatingKSFile(it) }
        }.build()
    }

    private fun createProviderPropertySpec(factoryClass: FactoryDeclaration): PropertySpec {
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

    private fun createGetFunSpec(
        factoryClasses: Sequence<FactoryDeclaration>,
    ): FunSpec {
        return FunSpec.builder("resolve").apply {
            returns(ANY.copy(nullable = true))
            addModifiers(KModifier.OVERRIDE)
            addParameter("id", Identifier::class)
            beginControlFlow("return when (id) {")
            factoryClasses.forEach {
                val name = Names.providerNameOf(it.identifier)
                addStatement("%T.identifier -> $name()", it.asClassName())
            }
            addStatement("else -> null")
            endControlFlow()
        }.build()
    }
}
