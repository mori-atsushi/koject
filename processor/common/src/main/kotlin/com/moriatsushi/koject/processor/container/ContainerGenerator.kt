package com.moriatsushi.koject.processor.container

import com.google.devtools.ksp.processing.Resolver
import com.moriatsushi.koject.processor.file.FileGenerator
import com.moriatsushi.koject.processor.symbol.AllFactoryDeclarations

internal class ContainerGenerator(
    private val fileGenerator: FileGenerator,
    private val containerFileSpecFactory: ContainerFileSpecFactory,
    private val startFileSpecFactory: StartFileSpecFactory,
) {
    fun generate(resolver: Resolver) {
        generateContainer(resolver)
        generateStart()
    }

    private fun generateContainer(resolver: Resolver) {
        val allFactories = AllFactoryDeclarations.of(resolver)
        val fileSpec = containerFileSpecFactory.create(allFactories)

        fileGenerator.createNewFile(
            fileSpec = fileSpec,
            aggregating = true,
        )
    }

    private fun generateStart() {
        val fileSpec = startFileSpecFactory.create()

        fileGenerator.createNewFile(
            fileSpec = fileSpec,
            aggregating = false,
        )
    }
}
