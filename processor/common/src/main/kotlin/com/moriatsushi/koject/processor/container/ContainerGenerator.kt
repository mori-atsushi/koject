package com.moriatsushi.koject.processor.container

import com.google.devtools.ksp.processing.Resolver
import com.moriatsushi.koject.processor.file.FileGenerator
import com.moriatsushi.koject.processor.symbol.AllFactoryDeclarations
import com.moriatsushi.koject.processor.symbol.collectAllFactoryDeclarations

internal class ContainerGenerator(
    private val dependencyValidator: DependencyValidator,
    private val fileGenerator: FileGenerator,
    private val componentContainerFileSpecFactory: ComponentContainerFileSpecFactory,
    private val appContainerFileSpecFactory: AppContainerFileSpecFactory,
    private val startFileSpecFactory: StartFileSpecFactory,
) {
    fun generate(resolver: Resolver) {
        val allFactories = resolver.collectAllFactoryDeclarations()
        dependencyValidator.validate(allFactories)

        generateContainer(allFactories)
        generateStart()
    }

    private fun generateContainer(allFactories: AllFactoryDeclarations) {
        val rootComponent = componentContainerFileSpecFactory.createRoot(
            allFactories.rootComponent,
            allFactories.extrasHolders,
        )

        fileGenerator.createNewFile(
            fileSpec = rootComponent,
            aggregating = true,
        )

        allFactories.childComponents.forEach {
            val container = componentContainerFileSpecFactory.createComponent(
                it,
                allFactories.rootComponent,
            )

            fileGenerator.createNewFile(
                fileSpec = container,
                aggregating = true,
            )
        }

        val app = appContainerFileSpecFactory.create(allFactories)

        fileGenerator.createNewFile(
            fileSpec = app,
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
