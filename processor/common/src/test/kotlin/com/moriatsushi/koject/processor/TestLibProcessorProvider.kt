package com.moriatsushi.koject.processor

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

internal class TestLibProcessorProvider(
    private val options: DIProcessorOptions = DIProcessorOptions(),
) : SymbolProcessorProvider {
    override fun create(
        environment: SymbolProcessorEnvironment,
    ): SymbolProcessor {
        return DIProcessorFactory(environment, options).createLibProcessor()
    }
}
