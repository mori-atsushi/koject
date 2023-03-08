package com.moriatsushi.koject.processor.component

import com.google.devtools.ksp.processing.Resolver
import com.moriatsushi.koject.processor.file.FileGenerator
import com.moriatsushi.koject.processor.symbol.findComponentDeclarations

internal class ComponentGenerator(
    private val fileSpecFactory: ComponentFileSpecFactory,
    private val fileGenerator: FileGenerator,
) {
    fun generate(resolver: Resolver) {
        val components = resolver.findComponentDeclarations()
        components.forEach {
            val fileSpec = fileSpecFactory.generate(it)
            fileGenerator.createNewFile(fileSpec, false)
        }
    }
}
