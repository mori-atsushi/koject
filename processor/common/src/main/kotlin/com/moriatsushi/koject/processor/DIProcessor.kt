package com.moriatsushi.koject.processor

import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.moriatsushi.koject.processor.factory.FactoryGenerator

internal class DIProcessor(
    private val factoryGenerator: FactoryGenerator,
) : SymbolProcessor {
    private var invoked = false
    override fun process(resolver: Resolver): List<KSAnnotated> {
        if (invoked) return emptyList()
        invoked = true

        factoryGenerator.generate(resolver)
        // TODO: generate container

        return emptyList()
    }
}
