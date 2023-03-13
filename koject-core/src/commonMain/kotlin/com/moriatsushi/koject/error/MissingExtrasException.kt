package com.moriatsushi.koject.error

import com.moriatsushi.koject.extras.KojectExtras

/**
 * [KojectExtras] is not set correctly.
 */
class MissingExtrasException(
    name: String,
    message: String?,
) : Exception(
    buildString {
        append(name)
        append(" is not set.")
        if (message != null) {
            append(" ")
            append(message)
        }
    },
)
