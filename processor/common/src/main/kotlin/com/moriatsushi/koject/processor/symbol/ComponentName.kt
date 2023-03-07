@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSDeclaration
import com.moriatsushi.koject.Component
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.internal.StringComponent
import com.moriatsushi.koject.processor.analytics.hasAnnotation
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.asClassName

data class ComponentName(
    val value: String,
) {
    companion object
}

internal fun ComponentName.asAnnotationSpec(): AnnotationSpec {
    return AnnotationSpec.builder(StringComponent::class.asClassName()).apply {
        addMember("%S", value)
    }.build()
}

internal fun ComponentName.Companion.of(declaration: KSDeclaration): ComponentName {
    val value = declaration.qualifiedName?.asString()!!
    return ComponentName(value)
}

internal fun KSAnnotated.findComponentName(): ComponentName? {
    val declaration = annotations
        .map { it.annotationType.resolve().declaration }
        .find { it.hasAnnotation<Component>() }
        ?: return null

    return ComponentName.of(declaration)
}
