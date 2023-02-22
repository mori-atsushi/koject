package com.moriatsushi.koject.internal.identifier

import kotlin.jvm.JvmInline

/**
 * Unique identifier for resolving dependencies
 */
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
