package com.moriatsushi.koject.processor.analytics

import com.google.devtools.ksp.symbol.FileLocation
import com.google.devtools.ksp.symbol.Location
import com.google.devtools.ksp.symbol.NonExistLocation

internal val Location.name: String
    get() = when (this) {
        is FileLocation -> "${this.filePath}:${this.lineNumber}"
        is NonExistLocation -> "unknown"
    }
