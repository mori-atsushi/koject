package com.moriatsushi.koject.processor.analytics

import com.google.devtools.ksp.symbol.FileLocation
import com.google.devtools.ksp.symbol.KSNode

internal val KSNode.locationString: String
    get() = (location as FileLocation).let {
        "${it.filePath}:${it.lineNumber}"
    }
