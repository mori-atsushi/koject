package com.moriatsushi.koject.processor

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.moriatsushi.koject.internal.InternalKojectApi
import com.moriatsushi.koject.processor.component.ComponentFileSpecFactory
import com.moriatsushi.koject.processor.component.ComponentGenerator
import com.moriatsushi.koject.processor.container.AppContainerFileSpecFactory
import com.moriatsushi.koject.processor.container.ComponentContainerFileSpecFactory
import com.moriatsushi.koject.processor.container.ContainerGenerator
import com.moriatsushi.koject.processor.container.DependencyValidator
import com.moriatsushi.koject.processor.container.StartFileSpecFactory
import com.moriatsushi.koject.processor.factory.FactoryFileSpecFactory
import com.moriatsushi.koject.processor.factory.FactoryGenerator
import com.moriatsushi.koject.processor.factory.ProviderValidator
import com.moriatsushi.koject.processor.file.FileGenerator

@InternalKojectApi
class DIProcessorFactory(
    private val environment: SymbolProcessorEnvironment,
) {
    private val fileGenerator by lazy {
        FileGenerator(environment.codeGenerator)
    }

    private fun createProviderValidator(): ProviderValidator {
        return ProviderValidator()
    }

    private fun createFactoryFileSpecFactory(): FactoryFileSpecFactory {
        return FactoryFileSpecFactory()
    }

    private fun createFactoryGenerator(): FactoryGenerator {
        return FactoryGenerator(
            fileGenerator,
            createProviderValidator(),
            createFactoryFileSpecFactory(),
        )
    }

    private fun createComponentFileSpecFactory(): ComponentFileSpecFactory {
        return ComponentFileSpecFactory()
    }

    private fun createComponentGenerator(): ComponentGenerator {
        return ComponentGenerator(
            createComponentFileSpecFactory(),
            fileGenerator,
        )
    }

    private fun createDependencyValidator(): DependencyValidator {
        return DependencyValidator()
    }

    private fun createComponentContainerFileSpecFactory(): ComponentContainerFileSpecFactory {
        return ComponentContainerFileSpecFactory()
    }

    private fun createAppContainerFileSpecFactory(): AppContainerFileSpecFactory {
        return AppContainerFileSpecFactory()
    }

    private fun createStartFileSpecFactory(): StartFileSpecFactory {
        return StartFileSpecFactory()
    }

    private fun createContainerGenerator(): ContainerGenerator {
        return ContainerGenerator(
            createDependencyValidator(),
            fileGenerator,
            createComponentContainerFileSpecFactory(),
            createAppContainerFileSpecFactory(),
            createStartFileSpecFactory(),
        )
    }

    fun create(
        shouldGenerateContainer: Boolean = true,
    ): SymbolProcessor {
        return DIProcessor(
            shouldGenerateContainer,
            createFactoryGenerator(),
            createComponentGenerator(),
            createContainerGenerator(),
            environment.codeGenerator,
        )
    }
}
