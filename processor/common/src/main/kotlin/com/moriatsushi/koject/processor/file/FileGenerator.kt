package com.moriatsushi.koject.processor.file

import com.google.devtools.ksp.processing.CodeGenerator
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.ksp.writeTo

internal class FileGenerator(
    private val codeGenerator: CodeGenerator,
) {
    fun createNewFile(
        fileSpec: FileSpec,
        aggregating: Boolean,
    ) {
        fileSpec.writeTo(codeGenerator, aggregating)
    }
}
