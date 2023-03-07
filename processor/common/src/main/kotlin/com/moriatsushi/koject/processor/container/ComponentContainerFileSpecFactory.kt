package com.moriatsushi.koject.processor.container

import com.moriatsushi.koject.internal.Identifier
import com.moriatsushi.koject.processor.code.AnnotationSpecFactory
import com.moriatsushi.koject.processor.code.Names
import com.moriatsushi.koject.processor.code.applyCommon
import com.moriatsushi.koject.processor.symbol.ComponentClassDeclaration
import com.moriatsushi.koject.processor.symbol.ComponentFactoryDeclarations
import com.moriatsushi.koject.processor.symbol.FactoryDeclaration
import com.moriatsushi.koject.processor.symbol.containerClassName
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

internal class ComponentContainerFileSpecFactory {
    fun createRoot(factories: ComponentFactoryDeclarations): FileSpec {
        val type = TypeSpec.classBuilder(Names.rootComponentContainerClassName).apply {
            factories.singletons.forEach {
                addProperty(createSingletonInstancePropertySpec(factories, it))
            }
            factories.normals.forEach {
                addProperty(createProviderPropertySpec(factories, it))
            }
            addFunction(createGetFunSpec(factories))
            addAnnotation(AnnotationSpecFactory.createInternal())

            factories.all
                .mapNotNull { it.containingFile }
                .forEach { addOriginatingKSFile(it) }
        }.build()

        return FileSpec.builder(
            Names.generatedPackageName,
            type.name!!,
        ).apply {
            applyCommon()
            addType(type)
        }.build()
    }

    fun createComponent(
        component: ComponentClassDeclaration,
        factories: ComponentFactoryDeclarations,
    ): FileSpec {
        val type = TypeSpec.classBuilder(component.containerClassName).apply {
            factories.normals.forEach {
                addProperty(createProviderPropertySpec(factories, it))
            }
            addFunction(createGetFunSpec(factories))
            addAnnotation(AnnotationSpecFactory.createInternal())

            factories.all
                .mapNotNull { it.containingFile }
                .forEach { addOriginatingKSFile(it) }
        }.build()

        return FileSpec.builder(
            Names.generatedPackageName,
            type.name!!,
        ).apply {
            applyCommon()
            addType(type)
        }.build()
    }

    private fun createSingletonInstancePropertySpec(
        factories: ComponentFactoryDeclarations,
        factoryClass: FactoryDeclaration,
    ): PropertySpec {
        val factoryCode = createFactoryCodeBlock(factories, factoryClass)
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
        factories: ComponentFactoryDeclarations,
        factoryClass: FactoryDeclaration,
    ): PropertySpec {
        val providerName = Names.providerNameOf(factoryClass.identifier)
        val factoryCode = createFactoryCodeBlock(factories, factoryClass)
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
        factories: ComponentFactoryDeclarations,
        factoryClass: FactoryDeclaration,
    ): CodeBlock {
        return buildCodeBlock {
            add("%T(", factoryClass.className)
            if (factoryClass.parameters.isNotEmpty()) {
                add("\n")
                indent()
                factoryClass.parameters.forEach {
                    val factory = factories.get(it.identifier)
                    if (factory.isSingleton) {
                        add("{ ${Names.instanceNameOf(it.identifier)} }")
                    } else {
                        add(Names.providerNameOf(it.identifier))
                    }
                    add(",\n")
                }
                unindent()
            }
            add(")")
        }
    }

    private fun createGetFunSpec(
        factories: ComponentFactoryDeclarations,
    ): FunSpec {
        return FunSpec.builder("resolve").apply {
            returns(ANY.copy(nullable = true))
            addParameter("id", Identifier::class)
            beginControlFlow("return when (id) {")
            factories.all.forEach {
                val factory = factories.get(it.identifier)
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
