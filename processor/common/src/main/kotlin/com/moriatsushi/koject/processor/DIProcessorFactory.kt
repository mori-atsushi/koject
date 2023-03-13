package com.moriatsushi.koject.processor

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.moriatsushi.koject.internal.InternalKojectApi
import com.moriatsushi.koject.processor.component.ComponentExtrasHolderFileSpecFactory
import com.moriatsushi.koject.processor.component.ComponentExtrasHolderGenerator
import com.moriatsushi.koject.processor.component.ComponentExtrasValidator
import com.moriatsushi.koject.processor.container.AppContainerFileSpecFactory
import com.moriatsushi.koject.processor.container.ComponentContainerFileSpecFactory
import com.moriatsushi.koject.processor.container.ContainerGenerator
import com.moriatsushi.koject.processor.container.DependencyValidator
import com.moriatsushi.koject.processor.container.StartFileSpecFactory
import com.moriatsushi.koject.processor.extras.ExtrasHolderFileSpecFactory
import com.moriatsushi.koject.processor.extras.ExtrasHolderGenerator
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

    private val extrasHolderFileSpecFactory by lazy {
        ExtrasHolderFileSpecFactory()
    }

    private fun createExtrasHolderGenerator(): ExtrasHolderGenerator {
        return ExtrasHolderGenerator(
            extrasHolderFileSpecFactory,
            fileGenerator,
        )
    }

    private fun createComponentExtrasValidator(): ComponentExtrasValidator {
        return ComponentExtrasValidator()
    }

    private fun createComponentExtrasHolderFileSpecFactory(): ComponentExtrasHolderFileSpecFactory {
        return ComponentExtrasHolderFileSpecFactory(
            extrasHolderFileSpecFactory,
        )
    }

    private fun createComponentExtrasHolderGenerator(): ComponentExtrasHolderGenerator {
        return ComponentExtrasHolderGenerator(
            createComponentExtrasHolderFileSpecFactory(),
            fileGenerator,
            createComponentExtrasValidator(),
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
        options: DIProcessorOptions = DIProcessorOptions(),
    ): SymbolProcessor {
        return DIProcessor(
            options,
            createFactoryGenerator(),
            createExtrasHolderGenerator(),
            createComponentExtrasHolderGenerator(),
            createContainerGenerator(),
            environment.codeGenerator,
        )
    }
}
