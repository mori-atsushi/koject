package com.moriatsushi.koject

/**
 * Marks it distributable with DI Container
 */
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class Provides

/**
 * Mark as a type that a DI container instantiate only once
 */
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class Singleton
