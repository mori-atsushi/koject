@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.isPrivate
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSType
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.component.ComponentExtras
import com.moriatsushi.koject.processor.analytics.findAnnotation
import com.moriatsushi.koject.processor.analytics.findArgumentByName
import com.moriatsushi.koject.processor.code.escapedForCode
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ksp.toClassName

internal data class ComponentExtrasDeclaration(
    val className: ClassName,
    val parameters: Sequence<ExtrasParameter>,
    val componentName: ComponentName,
    val containingFile: KSFile?,
) {
    companion object
}

/**
 * Name that can be used in code for functions, classes, etc.
 */
internal fun ComponentExtrasDeclaration.asCodeName(): String {
    return componentName.value.escapedForCode
}

internal fun Resolver.findComponentExtrasDeclarations(): Sequence<ComponentExtrasDeclaration> {
    return getSymbolsWithAnnotation(ComponentExtras::class.qualifiedName!!)
        .filterIsInstance<KSClassDeclaration>()
        .map { ComponentExtrasDeclaration.of(it) }
}

private fun ComponentExtrasDeclaration.Companion.of(
    declaration: KSClassDeclaration,
): ComponentExtrasDeclaration {
    return ComponentExtrasDeclaration(
        className = declaration.toClassName(),
        parameters = declaration.extrasParameters,
        componentName = declaration.componentName,
        containingFile = declaration.containingFile,
    )
}

private val KSClassDeclaration.componentName
    get() = findAnnotation<ComponentExtras>()!!
        .findArgumentByName<KSType>("of")!!
        .declaration
        .let { ComponentName.of(it) }

private val KSClassDeclaration.extrasParameters
    get() = getAllProperties()
        .filterNot { it.isPrivate() }
        .map { ExtrasParameter.of(it) }
