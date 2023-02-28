package com.moriatsushi.koject.processor.error

internal open class CodeGenerationException(
    override val message: String,
) : Exception(message)
