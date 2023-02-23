package com.moriatsushi.koject.processor

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.moriatsushi.koject.processor.factory.FactoryFileSpecFactory
import com.moriatsushi.koject.processor.factory.FactoryGenerator
import com.moriatsushi.koject.processor.file.FileGenerator

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

    fun create(): SymbolProcessor {
        return DIProcessor(createFactoryGenerator())
    }
}
