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
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER)
@Qualifier
annotation class Named(val name: String)

/**
 * Mark it to provide as a supertype.
 *
 * @param to Specifies the type when there are multiple supertypes.
 */
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS)
@Qualifier
annotation class Binds(val to: KClass<*> = Nothing::class)
