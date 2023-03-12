package com.moriatsushi.koject.internal

import com.moriatsushi.koject.component.Component

/**
 * String [Identifier]
 */
@InternalKojectApi
@Retention(value = AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.PROPERTY,
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
    AnnotationTarget.PROPERTY,
)
annotation class Location(val value: String)

/**
 * Marks declarations that are internal in Koject API
 *
 * Don't use it outside of Koject.
 * API will change without notice in future releases.
 */
@Retention(value = AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.TYPEALIAS,
    AnnotationTarget.PROPERTY,
)
@RequiresOptIn(
    level = RequiresOptIn.Level.ERROR,
    message = "This is an internal Koject API " +
        "that should not be used from outside of Koject. " +
        "API will change without notice in future releases.",
)
@MustBeDocumented
annotation class InternalKojectApi
