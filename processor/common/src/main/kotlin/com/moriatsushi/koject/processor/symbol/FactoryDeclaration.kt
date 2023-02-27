package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.moriatsushi.koject.Singleton
import com.moriatsushi.koject.internal.identifier.StringIdentifier
import com.moriatsushi.koject.processor.analytics.hasAnnotation
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ksp.toClassName

internal data class FactoryDeclaration(
    val identifier: StringIdentifier,
    val className: ClassName,
    val dependencies: List<StringIdentifier>,
    val isSingleton: Boolean,
    val containingFile: KSFile?,
) {
    companion object
}

internal fun FactoryDeclaration.Companion.of(
    ksClass: KSClassDeclaration,
): FactoryDeclaration {
    val dependencies = ksClass.primaryConstructor?.parameters
        .orEmpty()
        .map { it.findStringIdentifier()!! }
    return FactoryDeclaration(
        identifier = ksClass.findStringIdentifier()!!,
        className = ksClass.toClassName(),
        dependencies = dependencies,
        isSingleton = ksClass.hasAnnotation<Singleton>(),
        containingFile = ksClass.containingFile,
    )
}
