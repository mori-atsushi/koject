@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.isPrivate
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.extras.KojectExtras
import com.moriatsushi.koject.internal.Location
import com.moriatsushi.koject.processor.code.escapedForCode
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ksp.toClassName

internal data class ExtrasDeclaration(
    val className: ClassName,
    val extras: Sequence<ExtraDeclaration>,
    val location: Location,
    val containingFile: KSFile?,
) {
    fun asCodeName(): String {
        return className.canonicalName.escapedForCode
    }

    companion object
}

internal fun Resolver.findExtrasDeclarations(): Sequence<ExtrasDeclaration> {
    return getSymbolsWithAnnotation(KojectExtras::class.qualifiedName!!)
        .filterIsInstance<KSClassDeclaration>()
        .map { ExtrasDeclaration.of(it) }
}

internal fun ExtrasDeclaration.Companion.of(
    declaration: KSClassDeclaration,
): ExtrasDeclaration {
    return ExtrasDeclaration(
        className = declaration.toClassName(),
        extras = declaration.extrasParameters,
        location = declaration.createLocationAnnotation(),
        containingFile = declaration.containingFile,
    )
}

private val KSClassDeclaration.extrasParameters
    get() = getAllProperties()
        .filterNot { it.isPrivate() }
        .map { ExtraDeclaration.of(it) }
