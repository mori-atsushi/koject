package com.moriatsushi.koject

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
 * You can provide as a supertype.
 */
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS)
@Qualifier
annotation class Binds
