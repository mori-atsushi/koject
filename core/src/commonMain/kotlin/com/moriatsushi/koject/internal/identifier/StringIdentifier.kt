package com.moriatsushi.koject.internal.identifier

import com.moriatsushi.koject.internal.InternalKojectApi

/**
 * String [Identifier]
 */
@InternalKojectApi
@Retention(value = AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.VALUE_PARAMETER,
)
annotation class StringIdentifier(
    val type: String,
    val qualifier: String = "",
) {
    companion object
}
