@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSType
import com.moriatsushi.koject.Component
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.internal.StringComponent
import com.moriatsushi.koject.processor.analytics.findAnnotation
import com.moriatsushi.koject.processor.analytics.findArgumentByName
import com.moriatsushi.koject.processor.code.escapedForCode
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.ksp.toClassName

internal data class ComponentDeclaration(
    val name: ClassName,
    val arguments: ComponentArguments,
    val containingFile: KSFile?,
) {
    companion object
}

/**
 * Name that can be used in code for functions, classes, etc.
 */
internal fun ComponentDeclaration.asCodeName(): String {
    return name.canonicalName.escapedForCode
}

internal val ComponentDeclaration.stringComponentAnnotationSpec: AnnotationSpec
    get() = AnnotationSpec.builder(StringComponent::class.asClassName()).apply {
        addMember("%S", name.toString())
    }.build()

internal fun Resolver.findComponentDeclarations(): Sequence<ComponentDeclaration> {
    return getSymbolsWithAnnotation(Component.Arguments::class.qualifiedName!!)
        .filterIsInstance<KSClassDeclaration>()
        .map { ComponentDeclaration.of(it) }
}

private fun ComponentDeclaration.Companion.of(
    arguments: KSClassDeclaration,
): ComponentDeclaration {
    val component = arguments.findAnnotation<Component.Arguments>()!!
        .findArgumentByName<KSType>("of")!!
    return ComponentDeclaration(
        name = component.toClassName(),
        arguments = ComponentArguments(arguments.toClassName()),
        containingFile = arguments.containingFile,
    )
}
