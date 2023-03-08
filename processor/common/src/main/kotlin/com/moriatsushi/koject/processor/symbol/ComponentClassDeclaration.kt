package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.isPrivate
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.moriatsushi.koject.internal.StringIdentifier
import com.moriatsushi.koject.processor.code.Names
import com.moriatsushi.koject.processor.code.escapedForCode
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ksp.toClassName

internal data class ComponentClassDeclaration(
    val name: ComponentName,
    val className: ClassName,
    val extras: Sequence<Dependency>,
    val containingFile: KSFile?,
) {
    fun findDependency(identifier: StringIdentifier): Dependency? {
        return extras.find { it.identifier == identifier }
    }

    companion object
}

internal val ComponentClassDeclaration.containerClassName: ClassName
    get() = ClassName(
        Names.generatedPackageName,
        "_${name.value.escapedForCode}_Container",
    )

internal fun ComponentClassDeclaration.Companion.of(
    ksClass: KSClassDeclaration,
): ComponentClassDeclaration {
    return ComponentClassDeclaration(
        name = ksClass.findStringComponentName()!!,
        className = ksClass.toClassName(),
        extras = ksClass.extraParameters,
        containingFile = ksClass.containingFile,
    )
}

private val KSClassDeclaration.extraParameters: Sequence<Dependency>
    get() = getAllProperties()
        .filterNot { it.isPrivate() }
        .map { Dependency.of(it) }
