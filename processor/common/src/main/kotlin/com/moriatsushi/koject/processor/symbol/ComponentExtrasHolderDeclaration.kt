package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.isPrivate
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.moriatsushi.koject.internal.Location
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ksp.toClassName

internal class ComponentExtrasHolderDeclaration(
    val componentName: ComponentName,
    val className: ClassName,
    val extras: Sequence<Dependency>,
    val location: Location,
    val containingFile: KSFile?,
) {
    companion object
}

internal fun ComponentExtrasHolderDeclaration.Companion.of(
    ksClass: KSClassDeclaration,
): ComponentExtrasHolderDeclaration {
    val name = ksClass.findStringComponentName()!!

    return ComponentExtrasHolderDeclaration(
        componentName = name,
        className = ksClass.toClassName(),
        extras = ksClass.extraParameters,
        location = ksClass.findLocationAnnotation()!!,
        containingFile = ksClass.containingFile,
    )
}

private val KSClassDeclaration.extraParameters: Sequence<Dependency>
    get() = getAllProperties()
        .filterNot { it.isPrivate() }
        .map { Dependency.of(it) }
