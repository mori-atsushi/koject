package com.moriatsushi.koject.processor.factory

import com.google.devtools.ksp.processing.Resolver
import com.moriatsushi.koject.processor.file.FileGenerator
import com.moriatsushi.koject.processor.symbol.FactoryDeclaration
import com.moriatsushi.koject.processor.symbol.findFactories

internal class CopiedFactoryGenerator(
    private val moduleName: String?,
    private val fileGenerator: FileGenerator,
    private val factoryFileSpecFactory: CopiedFactoryFileSpecFactory,
) {
    fun generate(resolver: Resolver) {
        if (moduleName == null) return

        val factories = resolver.findFactories()
        factories
            .filter { it.containingFile == null } // The file is in the current module
            .groupBy { it.identifier to it.component }
            .forEach { (_, factories) ->
                val min = factories.minBy { it.copiedCount }
                processNode(min, moduleName)
            }
    }

    private fun processNode(factory: FactoryDeclaration, moduleName: String) {
        val fileSpec = factoryFileSpecFactory.create(factory, moduleName)
        fileGenerator.createNewFile(fileSpec, false)
    }
}
