package com.moriatsushi.koject.processor.component

import com.google.devtools.ksp.processing.Resolver
import com.moriatsushi.koject.processor.file.FileGenerator
import com.moriatsushi.koject.processor.symbol.findComponentExtrasDeclarations

internal class ComponentExtrasHolderGenerator(
    private val fileSpecFactory: ComponentExtrasHolderFileSpecFactory,
    private val fileGenerator: FileGenerator,
    private val validator: ComponentExtrasValidator,
) {
    fun generate(resolver: Resolver) {
        val extrasDeclarations = resolver.findComponentExtrasDeclarations()
        extrasDeclarations.forEach {
            validator.validate(it)
            val fileSpec = fileSpecFactory.generate(it)
            fileGenerator.createNewFile(fileSpec, false)
        }
    }
}
