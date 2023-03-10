package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.moriatsushi.koject.Singleton
import com.moriatsushi.koject.internal.Location
import com.moriatsushi.koject.processor.analytics.hasAnnotation

internal data class ExtraDeclaration(
    val name: String,
    val identifier: TypedIdentifier,
    val location: Location,
    val isSingleton: Boolean,
) {
    companion object
}

internal fun ExtraDeclaration.Companion.of(
    declaration: KSPropertyDeclaration,
): ExtraDeclaration {
    return ExtraDeclaration(
        name = declaration.simpleName.asString(),
        identifier = TypedIdentifier.of(declaration),
        location = declaration.createLocationAnnotation(),
        isSingleton = declaration.hasAnnotation<Singleton>(),
    )
}
