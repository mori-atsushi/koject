package com.moriatsushi.koject.processor

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.moriatsushi.koject.internal.InternalKojectApi

@OptIn(InternalKojectApi::class)
class AppProcessorProvider : SymbolProcessorProvider {
    override fun create(
        environment: SymbolProcessorEnvironment,
    ): SymbolProcessor {
        return DIProcessorFactory(environment).createAppProcessor()
    }
}
