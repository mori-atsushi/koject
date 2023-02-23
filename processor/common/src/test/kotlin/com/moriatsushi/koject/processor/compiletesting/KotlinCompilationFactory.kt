package com.moriatsushi.koject.processor.compiletesting

import com.moriatsushi.koject.processor.TestProcessorProvider
import com.tschuchort.compiletesting.KotlinCompilation
import com.tschuchort.compiletesting.SourceFile
import com.tschuchort.compiletesting.kspIncremental
import com.tschuchort.compiletesting.symbolProcessorProviders
import java.io.File

class KotlinCompilationFactory(
    private val workingDir: File,
) {
    fun create(
        vararg sourceFiles: SourceFile,
    ): KotlinCompilation {
        return KotlinCompilation().also {
            it.workingDir = workingDir
            it.sources = sourceFiles.asList()
            it.inheritClassPath = true
            it.symbolProcessorProviders = listOf(TestProcessorProvider())
            it.kspIncremental = true
            it.inheritClassPath = true
        }
    }
}
