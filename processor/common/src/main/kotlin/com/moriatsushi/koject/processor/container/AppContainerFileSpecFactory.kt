@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.processor.container

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.component.ComponentExtras
import com.moriatsushi.koject.error.MissingExtrasException
import com.moriatsushi.koject.extras.KojectExtras
import com.moriatsushi.koject.internal.Container
import com.moriatsushi.koject.internal.Identifier
import com.moriatsushi.koject.processor.code.AnnotationSpecFactory
import com.moriatsushi.koject.processor.code.applyCommon
import com.moriatsushi.koject.processor.symbol.ComponentDeclaration
import com.moriatsushi.koject.processor.symbol.ContainerDeclaration
import com.moriatsushi.koject.processor.symbol.ExtrasHolderDeclaration
import com.squareup.kotlinpoet.ANY
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.SET
import com.squareup.kotlinpoet.STAR
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asTypeName
import com.squareup.kotlinpoet.buildCodeBlock
import com.squareup.kotlinpoet.ksp.addOriginatingKSFile

internal class AppContainerFileSpecFactory {
    fun create(
        containerDeclaration: ContainerDeclaration,
    ): FileSpec {
        return FileSpec.builder(
            containerDeclaration.containerClassName.packageName,
            containerDeclaration.containerClassName.simpleName,
        ).apply {
            applyCommon()
            addType(createContainerClass(containerDeclaration))
        }.build()
    }

    private fun createContainerClass(
        containerDeclaration: ContainerDeclaration,
    ): TypeSpec {
        val internalAnnotationSpec =
            AnnotationSpecFactory.createInternal()

        return TypeSpec.classBuilder(containerDeclaration.containerClassName).apply {
            addSuperinterface(Container::class)
            primaryConstructor(createConstructorSpec())
            addProperty(
                createGlobalComponentPropertySpec(
                    containerDeclaration.rootComponent,
                ),
            )
            addInitializerBlock(
                createInitializerBlock(
                    containerDeclaration.rootComponent,
                ),
            )
            addFunction(createGetFunSpec(containerDeclaration.childComponents))
            addAnnotation(internalAnnotationSpec)

            containerDeclaration.childComponents
                .mapNotNull { it.extrasHolder.containingFile }
                .forEach { addOriginatingKSFile(it) }
        }.build()
    }

    private fun createConstructorSpec(): FunSpec {
        val extrasType = KojectExtras::class.asTypeName()
        return FunSpec.constructorBuilder().apply {
            addParameter("extras", SET.parameterizedBy(extrasType))
        }.build()
    }

    private fun createGlobalComponentPropertySpec(
        rootComponent: ComponentDeclaration,
    ): PropertySpec {
        val className = rootComponent.containerClassName
        return PropertySpec.builder("rootComponent", className).build()
    }

    private fun createInitializerBlock(
        rootComponent: ComponentDeclaration.Root,
    ): CodeBlock {
        val extrasHolders = rootComponent.extrasHolders
        return buildCodeBlock {
            extrasHolders.forEachIndexed { index, it ->
                add(createExtrasPropertyCode("extras$index", it))
            }
            add("rootComponent = %T(", rootComponent.containerClassName)
            if (extrasHolders.any()) {
                add("\n")
                indent()
                extrasHolders.forEachIndexed { index, _ ->
                    add("extras$index,\n")
                }
                unindent()
            }
            add(")\n")
        }
    }

    private fun createExtrasPropertyCode(
        name: String,
        extrasHolder: ExtrasHolderDeclaration,
    ): CodeBlock {
        val exceptionTypeName = MissingExtrasException::class.asTypeName()

        return buildCodeBlock {
            add("val $name = %T(\n", extrasHolder.className)
            indent()
            add("extras.find {\n")
            indent()
            add("it::class == %T.kClass\n", extrasHolder.className)
            unindent()
            add(
                "} ?: throw %T(%T.name, %T.message)\n",
                exceptionTypeName,
                extrasHolder.className,
                extrasHolder.className,
            )
            unindent()
            add(")\n")
        }
    }

    private fun createGetFunSpec(
        components: Sequence<ComponentDeclaration.Child>,
    ): FunSpec {
        val code = buildCodeBlock {
            add("if (componentExtras == null) {\n")
            indent()
            add("return rootComponent.resolve(id)\n")
            unindent()
            add("}\n")
            add("return when (componentExtras::class) {\n")
            indent()
            components.forEach {
                add("%T.kClass -> \n", it.extrasHolder.className)
                indent()
                add("%T(\n", it.containerClassName)
                indent()
                add("componentExtras,\n")
                add("rootComponent,\n")
                unindent()
                add(").resolve(id)\n")
                unindent()
            }
            add("else -> rootComponent.resolve(id)\n")
            unindent()
            add("}\n")
        }
        val componentExtrasType = ComponentExtras::class.asTypeName()
            .parameterizedBy(STAR)
            .copy(nullable = true)
        return FunSpec.builder("resolve").apply {
            returns(ANY.copy(nullable = true))
            addModifiers(KModifier.OVERRIDE)
            addParameter("id", Identifier::class)
            addParameter("componentExtras", componentExtrasType)
            addCode(code)
        }.build()
    }
}
