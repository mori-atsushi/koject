package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSAnnotated
import com.moriatsushi.koject.Singleton
import com.moriatsushi.koject.internal.ForTest
import com.moriatsushi.koject.internal.Location
import com.moriatsushi.koject.internal.StringIdentifier
import com.moriatsushi.koject.processor.analytics.hasAnnotation

internal data class Provided(
    val identifier: StringIdentifier,
    val location: Location,
    val isSingleton: Boolean,
    val forTest: Boolean,
) {
    companion object
}

internal fun Provided.Companion.of(
    declaration: KSAnnotated,
): Provided {
    return Provided(
        identifier = declaration.findStringIdentifier()!!,
        location = declaration.findLocationAnnotation()!!,
        isSingleton = declaration.hasAnnotation<Singleton>(),
        forTest = declaration.hasAnnotation<ForTest>(),
    )
}
