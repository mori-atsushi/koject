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
) {
    companion object {
        fun of(typeStruct: TypeStruct): Identifier {
            val value = typeStruct.toString()
            return Identifier(value)
        }
    }
}
