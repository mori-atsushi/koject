package com.moriatsushi.koject.processor.container

import com.google.devtools.ksp.processing.Resolver
import com.moriatsushi.koject.processor.file.FileGenerator
import com.moriatsushi.koject.processor.symbol.AllFactoryDeclarations
import com.moriatsushi.koject.processor.symbol.collectAllFactoryDeclarations

internal class ContainerGenerator(
    private val dependencyValidator: DependencyValidator,
    private val fileGenerator: FileGenerator,
    private val containerFileSpecFactory: ContainerFileSpecFactory,
    private val startFileSpecFactory: StartFileSpecFactory,
) {
    fun generate(resolver: Resolver) {
        val allFactories = resolver.collectAllFactoryDeclarations()
        dependencyValidator.validate(allFactories)

        generateContainer(allFactories)
        generateStart()
    }

    private fun generateContainer(allFactories: AllFactoryDeclarations) {
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
