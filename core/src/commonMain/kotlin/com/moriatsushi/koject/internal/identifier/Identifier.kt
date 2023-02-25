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
        fun of(typeStruct: TypeStruct, name: String? = null): Identifier {
            val value = buildString {
                append(typeStruct)
                if (name != null) {
                    append("-")
                    append(name)
                }
            }
            return Identifier(value)
        }

        inline fun <reified T> of(name: String? = null): Identifier {
            return of(TypeStruct.of<T>(), name)
        }
    }

    override fun compareTo(other: Identifier): Int {
        return value.compareTo(other.value)
    }

    override fun toString(): String {
        return value
    }
}
