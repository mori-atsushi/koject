@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSDeclaration
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.component.Component
import com.moriatsushi.koject.internal.StringComponent
import com.moriatsushi.koject.processor.analytics.findAnnotation
import com.moriatsushi.koject.processor.analytics.findArgumentByName
import com.moriatsushi.koject.processor.analytics.hasAnnotation
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.asClassName

internal data class ComponentName(
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

internal fun KSAnnotated.findStringComponentName(): ComponentName? {
    val name = findAnnotation<StringComponent>()
        ?.findArgumentByName<String>("name")
        ?: return null

    return ComponentName(name)
}
