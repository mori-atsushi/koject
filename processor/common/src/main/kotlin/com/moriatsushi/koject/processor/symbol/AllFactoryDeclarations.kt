package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.moriatsushi.koject.internal.identifier.Identifier
import com.moriatsushi.koject.processor.code.Names

internal class AllFactoryDeclarations(
    private val map: Map<Identifier, FactoryDeclaration>,
) {
    companion object {
        fun of(resolver: Resolver): AllFactoryDeclarations {
            @OptIn(KspExperimental::class)
            val all = resolver
                .getDeclarationsFromPackage(Names.factoryPackageName)
                .filterIsInstance<KSClassDeclaration>()
                .map { FactoryDeclaration(it) }
                .associateBy { it.identifier }
            return AllFactoryDeclarations(all)
        }
    }

    val all = map.values.sortedBy { it.identifier }
    val normals = all.filter { !it.isSingleton }
    val singletons = all.filter { it.isSingleton }

    fun get(identifier: Identifier): FactoryDeclaration {
        return getOrNull(identifier) ?: error("not found : $identifier")
    }

    fun getOrNull(identifier: Identifier): FactoryDeclaration? {
        return map[identifier]
    }
}
