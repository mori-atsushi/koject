package com.moriatsushi.koject.processor.analytics

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSType

internal val KSClassDeclaration.primarySuperType: KSType?
    get() = superTypes
        .map { it.resolve() }
        .filterNot { it.isAny }
        .firstOrNull()
