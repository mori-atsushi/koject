package com.moriatsushi.koject.processor.component

import com.moriatsushi.koject.processor.code.AnnotationSpecFactory
import com.moriatsushi.koject.processor.code.Names
import com.moriatsushi.koject.processor.code.applyCommon
import com.moriatsushi.koject.processor.symbol.ComponentDeclaration
import com.moriatsushi.koject.processor.symbol.asCodeName
import com.moriatsushi.koject.processor.symbol.stringComponentAnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.STAR
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asTypeName
import com.squareup.kotlinpoet.ksp.addOriginatingKSFile
import kotlin.reflect.KClass

internal class ComponentFileSpecFactory {
    fun generate(declaration: ComponentDeclaration): FileSpec {
        val classSpec = createComponentClassSpec(declaration)
        return FileSpec.builder(Names.componentPackageName, classSpec.name!!).apply {
            applyCommon()
            addType(classSpec)
        }.build()
    }

    private fun createComponentClassSpec(declaration: ComponentDeclaration): TypeSpec {
        val name = "_${declaration.asCodeName()}"
        return TypeSpec.objectBuilder(name).apply {
            addAnnotation(AnnotationSpecFactory.createInternal())
            addAnnotation(declaration.stringComponentAnnotationSpec)
            addProperty(createArgumentClassPropertySpec(declaration.arguments.name))

            if (declaration.containingFile != null) {
                addOriginatingKSFile(declaration.containingFile)
            }
        }.build()
    }

    private fun createArgumentClassPropertySpec(className: ClassName): PropertySpec {
        val type = KClass::class.asTypeName().parameterizedBy(STAR)
        return PropertySpec.builder("argumentClass", type).apply {
            initializer("%T::class", className)
        }.build()
    }
}
