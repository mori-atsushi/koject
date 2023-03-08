package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.moriatsushi.koject.internal.Location

internal data class ExtrasParameter(
    val name: String,
    val identifier: TypedIdentifier,
    val location: Location,
) {
    companion object
}

internal fun ExtrasParameter.Companion.of(
    declaration: KSPropertyDeclaration,
): ExtrasParameter {
    return ExtrasParameter(
        name = declaration.simpleName.asString(),
        identifier = TypedIdentifier.of(declaration),
        location = declaration.createLocationAnnotation(),
    )
}
