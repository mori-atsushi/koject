package com.moriatsushi.koject.internal

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
annotation class InternalKojectApi
