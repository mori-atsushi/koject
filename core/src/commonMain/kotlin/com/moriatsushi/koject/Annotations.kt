package com.moriatsushi.koject

import kotlin.reflect.KClass

/**
 * Marks it providable by Koject DI Container
 */
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class Provides

/**
 * Mark as a type that Koject DI container instantiate only once
 */
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class Singleton

/**
 * Identifies qualifier annotations
 */
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.ANNOTATION_CLASS)
annotation class Qualifier

/**
 * String-based [Qualifier]
 */
@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.VALUE_PARAMETER,
)
@Qualifier
annotation class Named(val name: String)

/**
 * Mark it to provide as a supertype.
 *
 * @param to Specifies the type when there are multiple supertypes.
 */
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS)
annotation class Binds(val to: KClass<*> = Nothing::class)

/**
 * Mark for dependency injection at runtime.
 *
 * Don't forget to provide using [ExtrasBuilder].
 * It is not checked to be provided at compile time.
 */
@ExperimentalKojectApi
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class Dynamic

/**
 * Marks declarations that is still experimental in Koject API
 *
 * API will change in future releases.
 */
@Retention(value = AnnotationRetention.BINARY)
@RequiresOptIn(
    level = RequiresOptIn.Level.WARNING,
    message = "This is still experimental Koject API " +
        "API will change in future releases.",
)
annotation class ExperimentalKojectApi
