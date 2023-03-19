package com.moriatsushi.koject.processor.compiletesting

import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.moriatsushi.koject.processor.TestAppProcessorProvider
import com.tschuchort.compiletesting.KotlinCompilation
import com.tschuchort.compiletesting.kspIncremental
import com.tschuchort.compiletesting.symbolProcessorProviders
import java.io.File

class KotlinCompilationFactory(
    private val symbolProcessorProviders: List<SymbolProcessorProvider> =
        listOf(TestAppProcessorProvider()),
) {
    fun create(workingDir: File): KotlinCompilation {
        return KotlinCompilation().also {
            it.workingDir = workingDir
            it.inheritClassPath = true
            it.symbolProcessorProviders = symbolProcessorProviders
            it.kspIncremental = true
            it.inheritClassPath = true
        }
    }
}
