@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSType
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.component.ComponentExtras
import com.moriatsushi.koject.processor.analytics.findAnnotation
import com.moriatsushi.koject.processor.analytics.findArgumentByName
import com.moriatsushi.koject.processor.code.escapedForCode

internal data class ComponentDeclaration(
    val name: ComponentName,
    val extras: ComponentExtrasDeclaration,
    val containingFile: KSFile?,
) {
    companion object
}

/**
 * Name that can be used in code for functions, classes, etc.
 */
internal fun ComponentDeclaration.asCodeName(): String {
    return name.value.escapedForCode
}

internal fun Resolver.findComponentDeclarations(): Sequence<ComponentDeclaration> {
    return getSymbolsWithAnnotation(ComponentExtras::class.qualifiedName!!)
        .filterIsInstance<KSClassDeclaration>()
        .map { ComponentDeclaration.of(it) }
}

private fun ComponentDeclaration.Companion.of(
    arguments: KSClassDeclaration,
): ComponentDeclaration {
    val component = arguments.findAnnotation<ComponentExtras>()!!
        .findArgumentByName<KSType>("of")!!
        .declaration
    return ComponentDeclaration(
        name = ComponentName.of(component),
        extras = ComponentExtrasDeclaration.of(arguments),
        containingFile = arguments.containingFile,
    )
}
