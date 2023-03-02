package com.moriatsushi.koject.processor

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.moriatsushi.koject.internal.InternalKojectApi
import com.moriatsushi.koject.processor.container.ContainerFileSpecFactory
import com.moriatsushi.koject.processor.container.ContainerGenerator
import com.moriatsushi.koject.processor.container.DependencyValidator
import com.moriatsushi.koject.processor.container.StartFileSpecFactory
import com.moriatsushi.koject.processor.factory.FactoryFileSpecFactory
import com.moriatsushi.koject.processor.factory.FactoryGenerator
import com.moriatsushi.koject.processor.file.FileGenerator

@InternalKojectApi
class DIProcessorFactory(
    private val environment: SymbolProcessorEnvironment,
) {
    private fun createFileGenerator(): FileGenerator {
        return FileGenerator(environment.codeGenerator)
    }

    private fun createFactoryFileSpecFactory(): FactoryFileSpecFactory {
        return FactoryFileSpecFactory()
    }

    private fun createFactoryGenerator(): FactoryGenerator {
        return FactoryGenerator(
            createFileGenerator(),
            createFactoryFileSpecFactory(),
        )
    }

    private fun createDependencyValidator(): DependencyValidator {
        return DependencyValidator()
    }

    private fun createContainerFileSpecFactory(): ContainerFileSpecFactory {
        return ContainerFileSpecFactory()
    }

    private fun createStartFileSpecFactory(): StartFileSpecFactory {
        return StartFileSpecFactory()
    }

    private fun createContainerGenerator(): ContainerGenerator {
        return ContainerGenerator(
            createDependencyValidator(),
            createFileGenerator(),
            createContainerFileSpecFactory(),
            createStartFileSpecFactory(),
        )
    }

    fun create(
        shouldGenerateContainer: Boolean = true,
    ): SymbolProcessor {
        return DIProcessor(
            shouldGenerateContainer,
            createFactoryGenerator(),
            createContainerGenerator(),
            environment.codeGenerator,
        )
    }
}
