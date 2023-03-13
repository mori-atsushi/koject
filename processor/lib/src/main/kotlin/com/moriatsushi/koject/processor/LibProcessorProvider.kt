package com.moriatsushi.koject.processor

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.moriatsushi.koject.internal.InternalKojectApi

@OptIn(InternalKojectApi::class)
class LibProcessorProvider : SymbolProcessorProvider {
    override fun create(
        environment: SymbolProcessorEnvironment,
    ): SymbolProcessor {
        // Don't generate a container class in the library module.
        val options = DIProcessorOptions(
            shouldGenerateContainer = false,
        )
        return DIProcessorFactory(environment).create(options)
    }
}
