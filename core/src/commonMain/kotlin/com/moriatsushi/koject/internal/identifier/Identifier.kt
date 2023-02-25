package com.moriatsushi.koject.internal.identifier

import com.moriatsushi.koject.internal.InternalKojectApi
import kotlin.reflect.KType
import kotlin.reflect.typeOf

/**
 * Unique identifier for resolving dependencies
 */
@InternalKojectApi
data class Identifier(
    val type: KType,
    val qualifier: Any?,
) : Comparable<Identifier> {
    companion object {
        inline fun <reified T> of(qualifier: Any? = null): Identifier {
            return Identifier(typeOf<T>(), qualifier)
        }
    }

    /**
     * Alphabetical order
     */
    override fun compareTo(other: Identifier): Int {
        return compareBy<Identifier>(
            { type.toString() },
            { qualifier?.toString().orEmpty() },
        ).compare(this, other)
    }
}
