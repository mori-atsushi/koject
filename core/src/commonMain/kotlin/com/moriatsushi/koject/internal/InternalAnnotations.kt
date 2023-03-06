package com.moriatsushi.koject.internal

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

/**
 * Definition location
 */
@InternalKojectApi
@Retention(value = AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.VALUE_PARAMETER,
)
annotation class Location(val value: String)
