package com.moriatsushi.koject.processor.container

import com.moriatsushi.koject.internal.Container
import com.moriatsushi.koject.internal.Identifier
import com.moriatsushi.koject.processor.code.AnnotationSpecFactory
import com.moriatsushi.koject.processor.code.Names
import com.moriatsushi.koject.processor.code.applyCommon
import com.moriatsushi.koject.processor.symbol.AllFactoryDeclarations
import com.moriatsushi.koject.processor.symbol.ComponentDeclaration
import com.moriatsushi.koject.processor.symbol.containerClassName
import com.squareup.kotlinpoet.ANY
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.buildCodeBlock

internal class AppContainerFileSpecFactory {
    fun create(
        allFactoryDeclarations: AllFactoryDeclarations,
    ): FileSpec {
        return FileSpec.builder(
            Names.appContainerClassName.packageName,
            Names.appContainerClassName.simpleName,
        ).apply {
            applyCommon()
            addType(createContainerClass(allFactoryDeclarations))
        }.build()
    }

    private fun createContainerClass(
        allFactoryDeclarations: AllFactoryDeclarations,
    ): TypeSpec {
        val internalAnnotationSpec =
            AnnotationSpecFactory.createInternal()

        return TypeSpec.classBuilder(Names.appContainerClassName).apply {
            addSuperinterface(Container::class)
            addProperty(createGlobalComponentPropertySpec(allFactoryDeclarations.rootComponent))
            addFunction(createGetFunSpec(allFactoryDeclarations.childComponents))
            addAnnotation(internalAnnotationSpec)
        }.build()
    }

    private fun createGlobalComponentPropertySpec(
        rootComponent: ComponentDeclaration,
    ): PropertySpec {
        val className = rootComponent.containerClassName
        return PropertySpec.builder("rootComponent", className).apply {
            initializer("%T()", className)
        }.build()
    }

    private fun createGetFunSpec(
        components: Sequence<ComponentDeclaration>,
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
                add("%T.argumentClass -> \n", it.className)
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
        return FunSpec.builder("resolve").apply {
            returns(ANY.copy(nullable = true))
            addModifiers(KModifier.OVERRIDE)
            addParameter("id", Identifier::class)
            addParameter("componentExtras", ANY.copy(nullable = true))
            addCode(code)
        }.build()
    }
}
