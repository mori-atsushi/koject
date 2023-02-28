package com.moriatsushi.koject.internal

import kotlin.reflect.KType
import kotlin.reflect.typeOf

/**
 * Unique identifier for resolving dependencies
 */
@InternalKojectApi
data class Identifier(
    val type: KType,
    val qualifier: Any?,
) {
    companion object {
        inline fun <reified T> of(qualifier: Any? = null): Identifier {
            return Identifier(typeOf<T>(), qualifier)
        }
    }
}
