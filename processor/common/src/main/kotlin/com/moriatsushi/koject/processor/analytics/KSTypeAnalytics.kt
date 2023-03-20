package com.moriatsushi.koject.processor.analytics

import com.google.devtools.ksp.symbol.KSType
import kotlin.reflect.KClass

internal fun KSType.isSame(kClass: KClass<*>): Boolean {
    return declaration.qualifiedName?.asString() == kClass.qualifiedName
}

internal val KSType.isAny: Boolean
    get() = isSame(Any::class)

internal val KSType.isNothing: Boolean
    get() = isSame(Nothing::class)
