package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.moriatsushi.koject.internal.StringIdentifier
import com.moriatsushi.koject.processor.code.Names

internal class AllFactoryDeclarations(
    sequence: Sequence<FactoryDeclaration>,
) {
    val all = sequence.sortedBy { it.identifier.displayName }
    val normals = all.filter { !it.isSingleton }
    val singletons = all.filter { it.isSingleton }

    fun get(identifier: StringIdentifier): FactoryDeclaration {
        return getOrNull(identifier) ?: error("not found : $identifier")
    }

    fun getOrNull(identifier: StringIdentifier): FactoryDeclaration? {
        return all.find { it.identifier == identifier }
    }

    companion object
}

internal fun Resolver.collectAllFactoryDeclarations(): AllFactoryDeclarations {
    @OptIn(KspExperimental::class)
    val all = getDeclarationsFromPackage(Names.factoryPackageName)
        .filterIsInstance<KSClassDeclaration>()
        .map { FactoryDeclaration.of(it) }
    return AllFactoryDeclarations(all)
}
