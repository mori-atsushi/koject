package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.moriatsushi.koject.Singleton
import com.moriatsushi.koject.internal.Location
import com.moriatsushi.koject.internal.StringIdentifier
import com.moriatsushi.koject.processor.analytics.hasAnnotation
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ksp.toClassName

internal data class FactoryDeclaration(
    val identifier: StringIdentifier,
    val className: ClassName,
    val parameters: List<FactoryParameter>,
    val isSingleton: Boolean,
    val location: Location,
) {
    companion object
}

internal fun FactoryDeclaration.Companion.of(
    ksClass: KSClassDeclaration,
): FactoryDeclaration {
    return FactoryDeclaration(
        identifier = ksClass.findStringIdentifier()!!,
        className = ksClass.toClassName(),
        parameters = ksClass.factoryParameters,
        isSingleton = ksClass.hasAnnotation<Singleton>(),
        location = ksClass.findLocationAnnotation()!!,
    )
}

private val KSClassDeclaration.factoryParameters: List<FactoryParameter>
    get() = primaryConstructor?.parameters
        .orEmpty()
        .map {
            FactoryParameter(
                identifier = it.findStringIdentifier()!!,
                location = it.findLocationAnnotation()!!,
            )
        }
