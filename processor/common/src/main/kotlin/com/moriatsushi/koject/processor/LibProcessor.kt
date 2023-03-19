package com.moriatsushi.koject.processor

import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.moriatsushi.koject.processor.component.ComponentExtrasHolderGenerator
import com.moriatsushi.koject.processor.debug.TimeMeasure
import com.moriatsushi.koject.processor.extras.ExtrasHolderGenerator
import com.moriatsushi.koject.processor.factory.CopiedFactoryGenerator
import com.moriatsushi.koject.processor.factory.FactoryGenerator

internal class LibProcessor(
    private val factoryGenerator: FactoryGenerator,
    private val copiedFactoryGenerator: CopiedFactoryGenerator,
    private val extrasHolderGenerator: ExtrasHolderGenerator,
    private val componentExtrasHolderGenerator: ComponentExtrasHolderGenerator,
    private val timeMeasure: TimeMeasure,
) : SymbolProcessor {
    private var invoked: Boolean = false

    override fun process(resolver: Resolver): List<KSAnnotated> {
        if (invoked) return emptyList()
        invoked = true

        timeMeasure.measure("LibProcessor") {
            copiedFactoryGenerator.generate(resolver)
            factoryGenerator.generate(resolver)
            extrasHolderGenerator.generate(resolver)
            componentExtrasHolderGenerator.generate(resolver)
        }

        return emptyList()
    }
}
