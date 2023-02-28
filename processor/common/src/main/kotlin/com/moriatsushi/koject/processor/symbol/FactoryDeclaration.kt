package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.moriatsushi.koject.Singleton
import com.moriatsushi.koject.internal.Location
import com.moriatsushi.koject.internal.identifier.StringIdentifier
import com.moriatsushi.koject.processor.analytics.hasAnnotation
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ksp.toClassName

internal data class FactoryDeclaration(
    val identifier: StringIdentifier,
    val className: ClassName,
    val parameters: List<FactoryParameter>,
    val isSingleton: Boolean,
    val location: Location,
    val containingFile: KSFile?,
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
        containingFile = ksClass.containingFile,
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
