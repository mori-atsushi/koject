package com.moriatsushi.koject.processor.component

import com.moriatsushi.koject.processor.code.Names
import com.moriatsushi.koject.processor.code.applyCommon
import com.moriatsushi.koject.processor.extras.ExtrasHolderFileSpecFactory
import com.moriatsushi.koject.processor.symbol.ComponentExtrasDeclaration
import com.moriatsushi.koject.processor.symbol.asAnnotationSpec
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.TypeSpec

internal class ComponentExtrasHolderFileSpecFactory(
    private val extrasHolderFileSpecFactory: ExtrasHolderFileSpecFactory,
) {
    fun generate(componentExtrasDeclaration: ComponentExtrasDeclaration): FileSpec {
        val classSpec = createClassSpec(componentExtrasDeclaration)
        return FileSpec.builder(Names.componentPackageName, classSpec.name!!).apply {
            applyCommon()
            addType(classSpec)
        }.build()
    }

    private fun createClassSpec(componentExtrasDeclaration: ComponentExtrasDeclaration): TypeSpec {
        return extrasHolderFileSpecFactory
            .createClassSpec(componentExtrasDeclaration.extras)
            .toBuilder()
            .apply {
                addAnnotation(componentExtrasDeclaration.componentName.asAnnotationSpec())
            }
            .build()
    }
}
