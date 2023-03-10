package com.moriatsushi.koject.processor.extras

import com.google.devtools.ksp.processing.Resolver
import com.moriatsushi.koject.processor.file.FileGenerator
import com.moriatsushi.koject.processor.symbol.findExtrasDeclarations

internal class ExtrasHolderGenerator(
    private val fileSpecFactory: ExtrasHolderFileSpecFactory,
    private val fileGenerator: FileGenerator,
) {
    fun generate(resolver: Resolver) {
        val extrasDeclarations = resolver.findExtrasDeclarations()
        extrasDeclarations.forEach {
            val fileSpec = fileSpecFactory.generate(it)
            fileGenerator.createNewFile(fileSpec, false)
        }
    }
}
