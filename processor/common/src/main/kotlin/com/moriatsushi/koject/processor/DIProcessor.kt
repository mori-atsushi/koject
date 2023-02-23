package com.moriatsushi.koject.processor

import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated

internal class DIProcessor : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        // TODO:
        return emptyList()
    }
}
