package com.moriatsushi.koject

import kotlin.reflect.KClass

/**
 * Marks it providable by Koject DI Container
 */
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@MustBeDocumented
annotation class Provides

/**
 * Mark as a type that Koject DI container instantiate only once
 */
@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY,
)
@MustBeDocumented
annotation class Singleton

/**
 * Identifies qualifier annotations
 */
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.ANNOTATION_CLASS)
@MustBeDocumented
annotation class Qualifier

/**
 * String-based [Qualifier]
 */
@Retention(AnnotationRetention.BINARY)
@Qualifier
@MustBeDocumented
annotation class Named(val name: String)

/**
 * Mark it to provide as a supertype.
 *
 * @param to Specifies the type when there are multiple supertypes.
 */
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS)
@MustBeDocumented
annotation class Binds(val to: KClass<*> = Nothing::class)

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
@MustBeDocumented
annotation class ExperimentalKojectApi
