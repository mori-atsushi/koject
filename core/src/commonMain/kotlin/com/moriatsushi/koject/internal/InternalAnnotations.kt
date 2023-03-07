package com.moriatsushi.koject.internal

import com.moriatsushi.koject.Component

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
 * String [Component]
 */
@InternalKojectApi
@Retention(value = AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS)
annotation class StringComponent(
    val name: String,
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
