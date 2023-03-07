package com.moriatsushi.koject.processor.container

import com.moriatsushi.koject.internal.Container
import com.moriatsushi.koject.internal.Identifier
import com.moriatsushi.koject.processor.code.AnnotationSpecFactory
import com.moriatsushi.koject.processor.code.Names
import com.moriatsushi.koject.processor.code.applyCommon
import com.moriatsushi.koject.processor.symbol.ComponentClassDeclaration
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
        components: Set<ComponentClassDeclaration>,
    ): FileSpec {
        return FileSpec.builder(
            Names.appContainerClassName.packageName,
            Names.appContainerClassName.simpleName,
        ).apply {
            applyCommon()
            addType(createContainerClass(components))
        }.build()
    }

    private fun createContainerClass(
        components: Set<ComponentClassDeclaration>,
    ): TypeSpec {
        val internalAnnotationSpec =
            AnnotationSpecFactory.createInternal()

        return TypeSpec.classBuilder(Names.appContainerClassName).apply {
            addSuperinterface(Container::class)
            addProperty(createGlobalComponentPropertySpec())
            addFunction(createGetFunSpec(components))
            addAnnotation(internalAnnotationSpec)
        }.build()
    }

    private fun createGlobalComponentPropertySpec(): PropertySpec {
        val className = Names.rootComponentContainerClassName
        return PropertySpec.builder("rootComponent", className).apply {
            initializer("%T()", className)
        }.build()
    }

    private fun createGetFunSpec(
        components: Set<ComponentClassDeclaration>,
    ): FunSpec {
        val code = buildCodeBlock {
            add("if (componentArguments == null) {\n")
            indent()
            add("return rootComponent.resolve(id)\n")
            unindent()
            add("}\n")
            add("return when (componentArguments::class) {\n")
            indent()
            components.forEach {
                add("%T.argumentClass -> ", it.className)
                add("%T().resolve(id)\n", it.containerClassName)
            }
            add("else -> rootComponent.resolve(id)\n")
            unindent()
            add("}\n")
        }
        return FunSpec.builder("resolve").apply {
            returns(ANY.copy(nullable = true))
            addModifiers(KModifier.OVERRIDE)
            addParameter("id", Identifier::class)
            addParameter("componentArguments", ANY.copy(nullable = true))
            addCode(code)
        }.build()
    }
}
