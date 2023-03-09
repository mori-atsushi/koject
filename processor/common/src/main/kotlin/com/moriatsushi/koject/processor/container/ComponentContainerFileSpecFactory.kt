package com.moriatsushi.koject.processor.container

import com.moriatsushi.koject.internal.Identifier
import com.moriatsushi.koject.processor.code.AnnotationSpecFactory
import com.moriatsushi.koject.processor.code.Names
import com.moriatsushi.koject.processor.code.applyCommon
import com.moriatsushi.koject.processor.symbol.ComponentDeclaration
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
    fun createRoot(component: ComponentDeclaration.Root): FileSpec {
        return internalCreateComponent(component, null)
    }

    fun createComponent(
        component: ComponentDeclaration.Child,
        rootComponent: ComponentDeclaration.Root,
    ): FileSpec {
        return internalCreateComponent(component, rootComponent)
    }

    private fun internalCreateComponent(
        component: ComponentDeclaration,
        rootComponent: ComponentDeclaration.Root?,
    ): FileSpec {
        val type = TypeSpec.classBuilder(component.containerClassName).apply {
            if (component is ComponentDeclaration.Child && rootComponent != null) {
                val extrasHolder = component.extrasHolder
                primaryConstructor(createChildComponentConstructorSpec(rootComponent))
                addProperty(createExtrasPropertySpec(extrasHolder.className))
                addProperty(createRootComponentPropertySpec(rootComponent))
                if (extrasHolder.containingFile != null) {
                    addOriginatingKSFile(extrasHolder.containingFile)
                }
            }

            component.singletonFactories.forEach {
                addProperty(createSingletonInstancePropertySpec(it, component))
            }

            component.normalFactories.forEach {
                addProperty(createProviderPropertySpec(it, component))
            }

            addFunction(createGetFunSpec(component.allFactories, rootComponent != null))
            addAnnotation(AnnotationSpecFactory.createInternal())

            component.allFactories
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

    private fun createChildComponentConstructorSpec(
        component: ComponentDeclaration.Root,
    ): FunSpec {
        val name = component.containerClassName
        return FunSpec.constructorBuilder().apply {
            addParameter("extras", ANY)
            addParameter("rootComponent", name)
        }.build()
    }

    private fun createExtrasPropertySpec(className: ClassName): PropertySpec {
        return PropertySpec.builder("extras", className).apply {
            initializer("%T(extras)", className)
            addModifiers(KModifier.PRIVATE)
        }.build()
    }

    private fun createRootComponentPropertySpec(
        component: ComponentDeclaration.Root,
    ): PropertySpec {
        val name = component.containerClassName
        return PropertySpec.builder("rootComponent", name).apply {
            initializer("rootComponent")
            addModifiers(KModifier.PRIVATE)
        }.build()
    }

    private fun createSingletonInstancePropertySpec(
        factoryClass: FactoryDeclaration,
        component: ComponentDeclaration,
    ): PropertySpec {
        val providerName = Names.providerNameOf(factoryClass.identifier)
        val factoryCode = createFactoryCodeBlock(factoryClass, component)
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
        factoryClass: FactoryDeclaration,
        component: ComponentDeclaration,
    ): PropertySpec {
        val providerName = Names.providerNameOf(factoryClass.identifier)
        val factoryCode = createFactoryCodeBlock(factoryClass, component)
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
        factoryClass: FactoryDeclaration,
        component: ComponentDeclaration,
    ): CodeBlock {
        return buildCodeBlock {
            add("%T(", factoryClass.className)
            if (factoryClass.parameters.isNotEmpty()) {
                add("\n")
                indent()
                factoryClass.parameters.forEach {
                    val code = findDependencyCode(it, component)
                    add("$code,\n")
                }
                unindent()
            }
            add(")")
        }
    }

    private fun findDependencyCode(
        target: Dependency,
        component: ComponentDeclaration,
    ): String {
        val providerName = Names.providerNameOf(target.identifier)
        if (component is ComponentDeclaration.Child) {
            val extraDependency = component.findExtraDependency(target.identifier)
            if (extraDependency != null) {
                return "this.extras.$providerName"
            }
        }
        val factory = component.findFactory(target.identifier)
        if (factory != null) {
            return providerName
        }
        return "this.rootComponent.$providerName"
    }

    private fun createGetFunSpec(
        factories: Sequence<FactoryDeclaration>,
        hasRootComponent: Boolean,
    ): FunSpec {
        return FunSpec.builder("resolve").apply {
            returns(ANY.copy(nullable = true))
            addParameter("id", Identifier::class)
            beginControlFlow("return when (id) {")
            factories.forEach {
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
