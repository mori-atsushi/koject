package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.moriatsushi.koject.processor.code.Names

internal class AllFactoryDeclarations(
    val all: Sequence<FactoryDeclaration>,
) {
    companion object {
        fun of(resolver: Resolver): AllFactoryDeclarations {
            @OptIn(KspExperimental::class)
            val all = resolver
                .getDeclarationsFromPackage(Names.factoryPackageName)
                .filterIsInstance<KSClassDeclaration>()
                .map { FactoryDeclaration(it) }
                .sortedBy { it.identifier }
            return AllFactoryDeclarations(all)
        }
    }
}
