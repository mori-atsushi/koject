package com.moriatsushi.koject

/**
 * Marks it distributable with DI Container
 */
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class Provides
