package com.moriatsushi.koject.processor.compiletesting

import com.moriatsushi.koject.processor.TestProcessorProvider
import com.tschuchort.compiletesting.KotlinCompilation
import com.tschuchort.compiletesting.kspIncremental
import com.tschuchort.compiletesting.symbolProcessorProviders
import java.io.File

class KotlinCompilationFactory {
    fun create(workingDir: File): KotlinCompilation {
        return KotlinCompilation().also {
            it.workingDir = workingDir
            it.inheritClassPath = true
            it.symbolProcessorProviders = listOf(TestProcessorProvider())
            it.kspIncremental = true
            it.inheritClassPath = true
        }
    }
}
