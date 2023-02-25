package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSDeclaration

internal val KSDeclaration.fullName: String
    get() = (qualifiedName ?: simpleName).asString()
