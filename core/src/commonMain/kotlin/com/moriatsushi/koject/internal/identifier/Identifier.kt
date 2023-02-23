package com.moriatsushi.koject.internal.identifier

import com.moriatsushi.koject.internal.InternalKojectApi
import kotlin.jvm.JvmInline

/**
 * Unique identifier for resolving dependencies
 */
@InternalKojectApi
@JvmInline
value class Identifier(
    val value: String,
) : Comparable<Identifier> {
    companion object {
        fun of(typeStruct: TypeStruct): Identifier {
            val value = typeStruct.toString()
            return Identifier(value)
        }

        inline fun <reified T> of(): Identifier {
            return of(TypeStruct.of<T>())
        }
    }

    override fun compareTo(other: Identifier): Int {
        return value.compareTo(other.value)
    }
}
