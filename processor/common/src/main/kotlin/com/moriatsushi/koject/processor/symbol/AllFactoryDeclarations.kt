package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.moriatsushi.koject.internal.StringIdentifier
import com.moriatsushi.koject.processor.code.Names

internal class AllFactoryDeclarations(
    private val map: Map<StringIdentifier, FactoryDeclaration>,
) {
    val all = map.values.sortedBy { it.identifier.type }
    val normals = all.filter { !it.isSingleton }
    val singletons = all.filter { it.isSingleton }

    fun get(identifier: StringIdentifier): FactoryDeclaration {
        return getOrNull(identifier) ?: error("not found : $identifier")
    }

    fun getOrNull(identifier: StringIdentifier): FactoryDeclaration? {
        return map[identifier]
    }

    companion object
}

internal fun Resolver.collectAllFactoryDeclarations(): AllFactoryDeclarations {
    @OptIn(KspExperimental::class)
    val all = getDeclarationsFromPackage(Names.factoryPackageName)
        .filterIsInstance<KSClassDeclaration>()
        .map { FactoryDeclaration.of(it) }
        .associateBy { it.identifier }
    return AllFactoryDeclarations(all)
}
