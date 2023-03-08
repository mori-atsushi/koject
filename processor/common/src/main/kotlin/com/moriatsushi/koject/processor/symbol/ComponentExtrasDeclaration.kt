package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.isPrivate
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ksp.toClassName

internal data class ComponentExtrasDeclaration(
    val name: ClassName,
    val parameters: Sequence<ExtrasParameter>,
) {
    companion object
}

internal fun ComponentExtrasDeclaration.Companion.of(
    declaration: KSClassDeclaration,
): ComponentExtrasDeclaration {
    return ComponentExtrasDeclaration(
        name = declaration.toClassName(),
        parameters = declaration
            .getAllProperties()
            .filterNot { it.isPrivate() }
            .map { ExtrasParameter.of(it) },
    )
}
