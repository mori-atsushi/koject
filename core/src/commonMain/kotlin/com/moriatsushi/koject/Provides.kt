package com.moriatsushi.koject

/**
 * Marks it distributable with DI Container
 */
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS)
annotation class Provides
