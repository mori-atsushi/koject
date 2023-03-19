package com.moriatsushi.koject.processor.component

import com.google.devtools.ksp.processing.Resolver
import com.moriatsushi.koject.processor.file.FileGenerator
import com.moriatsushi.koject.processor.symbol.findComponentExtrasHolders

internal class CopiedComponentExtrasHolderGenerator(
    private val moduleName: String?,
    private val fileSpecFactory: CopiedComponentExtrasHolderFileSpecFactory,
    private val fileGenerator: FileGenerator,
) {
    fun generate(resolver: Resolver) {
        if (moduleName == null) return

        val extrasDeclarations = resolver.findComponentExtrasHolders()
        extrasDeclarations
            .filter { it.containingFile == null } // The file is in the current module
            .groupBy { it.componentName }
            .forEach { (_, holders) ->
                val min = holders.minBy { it.copiedCount }
                val fileSpec = fileSpecFactory.create(min, moduleName)
                fileGenerator.createNewFile(fileSpec, false)
            }
    }
}
