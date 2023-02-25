package com.moriatsushi.koject.processor.identifier

import com.moriatsushi.koject.internal.identifier.Identifier

/**
 * Escaped Identifier that can be used as a method name
 */
internal val Identifier.escapedValue: String
    get() = value.replace(" ", "")
        .replace("-", "___")
        .replace(".", "_")
        .replace("<", "__")
        .replace(">", "__")
        .replace(",", "__")
        .replace("?", "_nullable")
