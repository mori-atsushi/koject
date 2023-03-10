@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSType
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.component.ComponentExtras
import com.moriatsushi.koject.processor.analytics.findAnnotation
import com.moriatsushi.koject.processor.analytics.findArgumentByName

internal data class ComponentExtrasDeclaration(
    val componentName: ComponentName,
    val extras: ExtrasDeclaration,
) {
    companion object
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
        componentName = declaration.componentName,
        extras = ExtrasDeclaration.of(declaration),
    )
}

private val KSClassDeclaration.componentName
    get() = findAnnotation<ComponentExtras>()!!
        .findArgumentByName<KSType>("of")!!
        .declaration
        .let { ComponentName.of(it) }
