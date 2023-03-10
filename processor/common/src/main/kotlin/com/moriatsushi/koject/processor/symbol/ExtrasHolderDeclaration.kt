package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.isPrivate
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.moriatsushi.koject.internal.Location
import com.moriatsushi.koject.internal.StringIdentifier
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ksp.toClassName

internal class ExtrasHolderDeclaration(
    val className: ClassName,
    val extras: Sequence<Dependency>,
    val location: Location,
    val containingFile: KSFile?,
) {
    fun findExtraDependency(identifier: StringIdentifier): Dependency? {
        return extras.find {
            it.identifier == identifier
        }
    }

    companion object
}

internal fun ExtrasHolderDeclaration.Companion.of(
    ksClass: KSClassDeclaration,
): ExtrasHolderDeclaration {
    return ExtrasHolderDeclaration(
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
