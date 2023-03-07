package com.moriatsushi.koject.processor.symbol

import com.moriatsushi.koject.internal.StringIdentifier

internal class ComponentFactoryDeclarations(
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
}
