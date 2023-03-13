@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.component.ComponentExtras
import com.moriatsushi.koject.processor.analytics.getClassDeclarationsWithSuperType

internal data class ComponentExtrasDeclaration(
    val componentName: ComponentName,
    val extras: ExtrasDeclaration,
) {
    companion object
}

internal fun Resolver.findComponentExtrasDeclarations(): Sequence<ComponentExtrasDeclaration> {
    return getClassDeclarationsWithSuperType(componentExtrasName)
        .filterNot { it.isExpect }
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

private val KSClassDeclaration.componentName: ComponentName
    get() {
        val superType = superTypes
            .map { it.resolve() }
            .find { it.declaration.qualifiedName?.asString() == componentExtrasName }
        val componentType = superType?.arguments?.first()?.type?.resolve()
            ?: error("Not found component type")
        return ComponentName.of(componentType.declaration)
    }

private val componentExtrasName = ComponentExtras::class.qualifiedName
    ?: error("Not found qualifiedName of ComponentExtras")
