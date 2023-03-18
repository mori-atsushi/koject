package com.moriatsushi.koject.processor.container

import com.google.devtools.ksp.processing.Resolver
import com.moriatsushi.koject.processor.analytics.includeTest
import com.moriatsushi.koject.processor.file.FileGenerator
import com.moriatsushi.koject.processor.symbol.AllFactoryDeclarations
import com.moriatsushi.koject.processor.symbol.collectAllFactoryDeclarations

internal class ContainerGenerator(
    private val dependencyValidator: DependencyValidator,
    private val fileGenerator: FileGenerator,
    private val componentContainerFileSpecFactory: ComponentContainerFileSpecFactory,
    private val appContainerFileSpecFactory: AppContainerFileSpecFactory,
    private val kojectFileSpecFactory: KojectFileSpecFactory,
    private val kojectTestFileSpecFactory: KojectTestFileSpecFactory,
) {
    fun generate(resolver: Resolver) {
        val includeTest = resolver.includeTest()

        val allFactories = resolver.collectAllFactoryDeclarations()
        dependencyValidator.validate(allFactories)

        generateContainer(allFactories)
        generateEntry(includeTest)
    }

    private fun generateContainer(allFactories: AllFactoryDeclarations) {
        val rootComponent = componentContainerFileSpecFactory
            .createRoot(allFactories.rootComponent)

        fileGenerator.createNewFile(
            fileSpec = rootComponent,
            aggregating = true,
        )

        allFactories.childComponents.forEach {
            val container = componentContainerFileSpecFactory
                .createComponent(it)

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

    private fun generateEntry(includeTest: Boolean) {
        val kojectFileSpec = kojectFileSpecFactory.create()

        fileGenerator.createNewFile(
            fileSpec = kojectFileSpec,
            aggregating = false,
        )

        if (includeTest) {
            val kojectTestFileSpec = kojectTestFileSpecFactory.create()

            fileGenerator.createNewFile(
                fileSpec = kojectTestFileSpec,
                aggregating = false,
            )
        }
    }
}
