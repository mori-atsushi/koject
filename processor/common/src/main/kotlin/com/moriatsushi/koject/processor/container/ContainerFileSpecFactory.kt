package com.moriatsushi.koject.processor.container

import com.moriatsushi.koject.internal.Container
import com.moriatsushi.koject.internal.identifier.Identifier
import com.moriatsushi.koject.processor.code.AnnotationSpecFactory
import com.moriatsushi.koject.processor.code.Names
import com.moriatsushi.koject.processor.code.applyCommon
import com.moriatsushi.koject.processor.symbol.AllFactoryDeclarations
import com.moriatsushi.koject.processor.symbol.FactoryDeclaration
import com.squareup.kotlinpoet.ANY
import com.squareup.kotlinpoet.CodeBlock
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
        return FileSpec.builder(
            Names.containerClassName.packageName,
            Names.containerClassName.simpleName,
        ).apply {
            applyCommon()
            addType(createContainerClass(allFactories))
        }.build()
    }

    private fun createContainerClass(allFactories: AllFactoryDeclarations): TypeSpec {
        val internalAnnotationSpec =
            AnnotationSpecFactory.createInternal()

        return TypeSpec.classBuilder(Names.containerClassName).apply {
            addSuperinterface(Container::class)
            allFactories.singletons.forEach {
                addProperty(createSingletonInstancePropertySpec(allFactories, it))
            }
            allFactories.normals.forEach {
                addProperty(createProviderPropertySpec(allFactories, it))
            }
            addFunction(createGetFunSpec(allFactories))
            addAnnotation(internalAnnotationSpec)

            allFactories.all
                .mapNotNull { it.containingFile }
                .forEach { addOriginatingKSFile(it) }
        }.build()
    }

    private fun createSingletonInstancePropertySpec(
        allFactories: AllFactoryDeclarations,
        factoryClass: FactoryDeclaration,
    ): PropertySpec {
        val factoryCode = createFactoryCodeBlock(allFactories, factoryClass)
        val instanceName = Names.instanceNameOf(factoryClass.identifier)
        val code = buildCodeBlock {
            add("lazy·{\n")
            indent()
            add(factoryCode)
            add(".create()\n")
            unindent()
            add("}")
        }

        return PropertySpec.builder(instanceName, ANY).apply {
            addModifiers(KModifier.PRIVATE)
            delegate(code)
        }.build()
    }

    private fun createProviderPropertySpec(
        allFactories: AllFactoryDeclarations,
        factoryClass: FactoryDeclaration,
    ): PropertySpec {
        val providerName = Names.providerNameOf(factoryClass.identifier)
        val factoryCode = createFactoryCodeBlock(allFactories, factoryClass)
        val type = LambdaTypeName.get(returnType = ANY)
        val code = buildCodeBlock {
            add("lazy·{\n")
            indent()
            add(factoryCode)
            add("::create\n")
            unindent()
            add("}")
        }

        return PropertySpec.builder(providerName, type).apply {
            addModifiers(KModifier.PRIVATE)
            delegate(code)
        }.build()
    }

    private fun createFactoryCodeBlock(
        allFactories: AllFactoryDeclarations,
        factoryClass: FactoryDeclaration,
    ): CodeBlock {
        return buildCodeBlock {
            add("%T(", factoryClass.className)
            if (factoryClass.dependencies.isNotEmpty()) {
                add("\n")
                indent()
                factoryClass.dependencies.forEach {
                    val factory = allFactories.get(it)
                    if (factory.isSingleton) {
                        add("{ ${Names.instanceNameOf(it)} }")
                    } else {
                        add(Names.providerNameOf(it))
                    }
                    add(",\n")
                }
                unindent()
            }
            add(")")
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
                val factory = allFactories.get(it.identifier)
                if (factory.isSingleton) {
                    val name = Names.instanceNameOf(it.identifier)
                    addStatement("%T.identifier -> $name", it.className)
                } else {
                    val name = Names.providerNameOf(it.identifier)
                    addStatement("%T.identifier -> $name()", it.className)
                }
            }
            addStatement("else -> null")
            endControlFlow()
        }.build()
    }
}
