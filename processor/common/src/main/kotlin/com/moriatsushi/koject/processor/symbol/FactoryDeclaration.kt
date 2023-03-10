package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.moriatsushi.koject.Singleton
import com.moriatsushi.koject.internal.Location
import com.moriatsushi.koject.internal.StringIdentifier
import com.moriatsushi.koject.processor.analytics.hasAnnotation
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ksp.toClassName

internal data class FactoryDeclaration(
    val identifier: StringIdentifier,
    val component: ComponentName?,
    val className: ClassName,
    val parameters: List<Dependency>,
    val isSingleton: Boolean,
    val location: Location,
    val containingFile: KSFile?,
) {
    fun asProvided(): Provided {
        return Provided(
            identifier = identifier,
            location = location,
            isSingleton = isSingleton,
        )
    }

    companion object
}

internal fun FactoryDeclaration.Companion.of(
    ksClass: KSClassDeclaration,
): FactoryDeclaration {
    return FactoryDeclaration(
        identifier = ksClass.findStringIdentifier()!!,
        component = ksClass.findStringComponentName(),
        className = ksClass.toClassName(),
        parameters = ksClass.factoryParameters,
        isSingleton = ksClass.hasAnnotation<Singleton>(),
        location = ksClass.findLocationAnnotation()!!,
        containingFile = ksClass.containingFile,
    )
}

private val KSClassDeclaration.factoryParameters: List<Dependency>
    get() = primaryConstructor?.parameters
        .orEmpty()
        .map { Dependency.of(it) }
