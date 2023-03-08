package com.moriatsushi.koject.processor.component

import com.google.devtools.ksp.processing.Resolver
import com.moriatsushi.koject.processor.file.FileGenerator
import com.moriatsushi.koject.processor.symbol.findComponentExtrasDeclarations

internal class ComponentGenerator(
    private val fileSpecFactory: ComponentFileSpecFactory,
    private val fileGenerator: FileGenerator,
) {
    fun generate(resolver: Resolver) {
        val extrasDeclarations = resolver.findComponentExtrasDeclarations()
        extrasDeclarations.forEach {
            val fileSpec = fileSpecFactory.generate(it)
            fileGenerator.createNewFile(fileSpec, false)
        }
    }
}
