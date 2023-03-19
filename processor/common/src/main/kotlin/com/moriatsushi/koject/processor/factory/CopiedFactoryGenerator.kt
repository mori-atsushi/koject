package com.moriatsushi.koject.processor.factory

import com.google.devtools.ksp.processing.Resolver
import com.moriatsushi.koject.processor.file.FileGenerator
import com.moriatsushi.koject.processor.symbol.FactoryDeclaration
import com.moriatsushi.koject.processor.symbol.findFactories

internal class CopiedFactoryGenerator(
    private val fileGenerator: FileGenerator,
    private val factoryFileSpecFactory: CopiedFactoryFileSpecFactory,
) {
    fun generate(resolver: Resolver) {
        val factories = resolver.findFactories()
        factories
            .groupBy { it.identifier to it.component }
            .forEach { (_, factories) ->
                val min = factories.minBy { it.copiedCount }
                processNode(min)
            }
    }

    private fun processNode(factory: FactoryDeclaration) {
        if (factory.containingFile != null) {
            // The file is in the current module
            return
        }

        val fileSpec = factoryFileSpecFactory.create(factory)
        fileGenerator.createNewFile(fileSpec, false)
    }
}
