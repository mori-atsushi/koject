package com.moriatsushi.koject.processor.error

import com.google.devtools.ksp.symbol.KSNode

internal open class CodeGenerationException(
    override val message: String,
    val symbol: KSNode? = null,
) : Exception(message)
