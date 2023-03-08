package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSAnnotated
import com.moriatsushi.koject.internal.Location
import com.moriatsushi.koject.internal.StringIdentifier

internal data class Dependency(
    val identifier: StringIdentifier,
    val location: Location,
) {
    companion object
}

internal fun Dependency.Companion.of(
    declaration: KSAnnotated,
): Dependency {
    return Dependency(
        identifier = declaration.findStringIdentifier()!!,
        location = declaration.findLocationAnnotation()!!,
    )
}
