package com.moriatsushi.koject.processor.container

import com.moriatsushi.koject.processor.file.FileGenerator
import com.moriatsushi.koject.processor.symbol.ContainerDeclaration

internal class ContainerGenerator(
    private val containerValidator: ContainerValidator,
    private val componentContainerFileSpecFactory: ComponentContainerFileSpecFactory,
    private val appContainerFileSpecFactory: AppContainerFileSpecFactory,
    private val fileGenerator: FileGenerator,
) {
    fun generate(allFactories: ContainerDeclaration) {
        containerValidator.validate(allFactories)

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
}
