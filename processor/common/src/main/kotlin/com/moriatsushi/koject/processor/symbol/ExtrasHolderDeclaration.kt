package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.isPrivate
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.moriatsushi.koject.internal.Location
import com.moriatsushi.koject.processor.code.Names
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ksp.toClassName

internal class ExtrasHolderDeclaration(
    val className: ClassName,
    val extras: Sequence<Provided>,
    val location: Location,
    val containingFile: KSFile?,
) {
    companion object
}

internal fun Resolver.findExtrasHolders(): Sequence<ExtrasHolderDeclaration> {
    @OptIn(KspExperimental::class)
    return getDeclarationsFromPackage(Names.extrasPackageName)
        .filterIsInstance<KSClassDeclaration>()
        .map { ExtrasHolderDeclaration.of(it) }
}

internal fun ExtrasHolderDeclaration.Companion.of(
    ksClass: KSClassDeclaration,
): ExtrasHolderDeclaration {
    return ExtrasHolderDeclaration(
        className = ksClass.toClassName(),
        extras = ksClass.extras,
        location = ksClass.findLocationAnnotation()!!,
        containingFile = ksClass.containingFile,
    )
}

private val KSClassDeclaration.extras: Sequence<Provided>
    get() = getAllProperties()
        .filterNot { it.isPrivate() }
        .map { Provided.of(it) }
