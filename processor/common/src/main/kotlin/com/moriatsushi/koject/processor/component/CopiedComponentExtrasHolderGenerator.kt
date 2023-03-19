package com.moriatsushi.koject.processor.component

import com.google.devtools.ksp.processing.Resolver
import com.moriatsushi.koject.processor.file.FileGenerator
import com.moriatsushi.koject.processor.symbol.findComponentExtrasHolders

internal class CopiedComponentExtrasHolderGenerator(
    private val fileSpecFactory: CopiedComponentExtrasHolderFileSpecFactory,
    private val fileGenerator: FileGenerator,
) {
    fun generate(resolver: Resolver) {
        val extrasDeclarations = resolver.findComponentExtrasHolders()
        extrasDeclarations
            .filter { it.containingFile == null } // The file is in the current module
            .groupBy { it.componentName }
            .forEach { (_, holders) ->
                val min = holders.minBy { it.copiedCount }
                val fileSpec = fileSpecFactory.generate(min)
                fileGenerator.createNewFile(fileSpec, false)
            }
    }
}
