package com.moriatsushi.koject.processor.container

import com.moriatsushi.koject.internal.Identifier
import com.moriatsushi.koject.processor.code.AnnotationSpecFactory
import com.moriatsushi.koject.processor.code.Names
import com.moriatsushi.koject.processor.code.applyCommon
import com.moriatsushi.koject.processor.symbol.ComponentClassDeclaration
import com.moriatsushi.koject.processor.symbol.ComponentFactoryDeclarations
import com.moriatsushi.koject.processor.symbol.Dependency
import com.moriatsushi.koject.processor.symbol.FactoryDeclaration
import com.moriatsushi.koject.processor.symbol.containerClassName
import com.squareup.kotlinpoet.ANY
import com.squareup.kotlinpoet.ClassName
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
                addProperty(createProviderPropertySpec(factories, it, null))
            }
            addFunction(createGetFunSpec(factories, false))
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
        val constructor = FunSpec.constructorBuilder().apply {
            addParameter("extras", ANY)
            addParameter(
                "rootComponent",
                Names.rootComponentContainerClassName,
            )
        }.build()

        val type = TypeSpec.classBuilder(component.containerClassName).apply {
            primaryConstructor(constructor)
            addProperty(createExtrasPropertySpec(component.className))
            addProperty(createRootComponentPropertySpec())

            factories.normals.forEach {
                addProperty(createProviderPropertySpec(factories, it, component))
            }
            addFunction(createGetFunSpec(factories, true))
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

    private fun createExtrasPropertySpec(className: ClassName): PropertySpec {
        return PropertySpec.builder("extras", className).apply {
            initializer("%T(extras)", className)
            addModifiers(KModifier.PRIVATE)
        }.build()
    }

    private fun createRootComponentPropertySpec(): PropertySpec {
        return PropertySpec.builder(
            "rootComponent",
            Names.rootComponentContainerClassName,
        ).apply {
            initializer("rootComponent")
            addModifiers(KModifier.PRIVATE)
        }.build()
    }

    private fun createSingletonInstancePropertySpec(
        factories: ComponentFactoryDeclarations,
        factoryClass: FactoryDeclaration,
    ): PropertySpec {
        val providerName = Names.providerNameOf(factoryClass.identifier)
        val factoryCode = createFactoryCodeBlock(factories, factoryClass, null)
        val type = LambdaTypeName.get(returnType = ANY)
        val code = buildCodeBlock {
            add("lazy·{\n")
            indent()
            add("val value = ")
            add(factoryCode)
            add(".create()\n")
            add("return@lazy { value }\n")
            unindent()
            add("}")
        }

        return PropertySpec.builder(providerName, type).apply {
            delegate(code)
        }.build()
    }

    private fun createProviderPropertySpec(
        factories: ComponentFactoryDeclarations,
        factoryClass: FactoryDeclaration,
        component: ComponentClassDeclaration?,
    ): PropertySpec {
        val providerName = Names.providerNameOf(factoryClass.identifier)
        val factoryCode = createFactoryCodeBlock(factories, factoryClass, component)
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
            delegate(code)
        }.build()
    }

    private fun createFactoryCodeBlock(
        factories: ComponentFactoryDeclarations,
        factoryClass: FactoryDeclaration,
        component: ComponentClassDeclaration?,
    ): CodeBlock {
        return buildCodeBlock {
            add("%T(", factoryClass.className)
            if (factoryClass.parameters.isNotEmpty()) {
                add("\n")
                indent()
                factoryClass.parameters.forEach {
                    val code = findDependencyCode(it, factories, component)
                    add("$code,\n")
                }
                unindent()
            }
            add(")")
        }
    }

    private fun findDependencyCode(
        target: Dependency,
        factories: ComponentFactoryDeclarations,
        component: ComponentClassDeclaration?,
    ): String {
        val providerName = Names.providerNameOf(target.identifier)
        val extraDependency = component?.findDependency(target.identifier)
        if (extraDependency != null) {
            return "this.extras.$providerName"
        }
        val factory = factories.getOrNull(target.identifier)
        if (factory != null) {
            return providerName
        }
        return "this.rootComponent.$providerName"
    }

    private fun createGetFunSpec(
        factories: ComponentFactoryDeclarations,
        hasRootComponent: Boolean,
    ): FunSpec {
        return FunSpec.builder("resolve").apply {
            returns(ANY.copy(nullable = true))
            addParameter("id", Identifier::class)
            beginControlFlow("return when (id) {")
            factories.all.forEach {
                val name = Names.providerNameOf(it.identifier)
                addStatement("%T.identifier -> $name()", it.className)
            }
            if (hasRootComponent) {
                addStatement("else -> rootComponent.resolve(id)")
            } else {
                addStatement("else -> null")
            }
            endControlFlow()
        }.build()
    }
}
