package com.moriatsushi.koject.test

import com.moriatsushi.koject.Provides

/**
 * [Provides] for testing
 *
 * Already provided dependencies can be overridden.
 */
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@MustBeDocumented
annotation class TestProvides
