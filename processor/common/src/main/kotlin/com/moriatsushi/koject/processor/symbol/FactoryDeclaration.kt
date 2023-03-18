package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.moriatsushi.koject.internal.Location
import com.moriatsushi.koject.internal.StringIdentifier
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ksp.toClassName

internal data class FactoryDeclaration(
    val provided: Provided,
    val component: ComponentName?,
    val className: ClassName,
    val parameters: List<Dependency>,
    val containingFile: KSFile?,
) {
    val identifier: StringIdentifier
        get() = provided.identifier

    val isSingleton: Boolean
        get() = provided.isSingleton

    val forTest: Boolean
        get() = provided.forTest

    val location: Location
        get() = provided.location

    companion object
}

internal fun FactoryDeclaration.Companion.of(
    ksClass: KSClassDeclaration,
): FactoryDeclaration {
    return FactoryDeclaration(
        provided = Provided.of(ksClass),
        component = ksClass.findStringComponentName(),
        className = ksClass.toClassName(),
        parameters = ksClass.factoryParameters,
        containingFile = ksClass.containingFile,
    )
}

private val KSClassDeclaration.factoryParameters: List<Dependency>
    get() = primaryConstructor?.parameters
        .orEmpty()
        .map { Dependency.of(it) }
