package com.moriatsushi.koject.processor

import com.google.devtools.ksp.processing.SymbolProcessor

class DIProcessorFactory {
    fun create(): SymbolProcessor {
        return DIProcessor()
    }
}
