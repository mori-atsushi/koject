package com.moriatsushi.koject.processor.analytics

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSType

internal val KSClassDeclaration.primarySuperType: KSType?
    get() = superTypes
        .map { it.resolve() }
        .filterNot { it.isAny }
        .firstOrNull()

internal fun KSClassDeclaration.hasSuperType(ksType: KSType): Boolean {
    superTypes
        .map { it.resolve() }
        .forEach {
            if (it == ksType) return true

            val declaration = it.declaration
            if (declaration is KSClassDeclaration && declaration.hasSuperType(ksType)) {
                return true
            }
        }
    return false
}
