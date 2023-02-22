package com.moriatsushi.koject.identifier

import kotlin.jvm.JvmInline

/**
 * Unique identifier for resolving dependencies
 */
@JvmInline
value class Identifier(
    val value: String,
) {
    companion object
}
