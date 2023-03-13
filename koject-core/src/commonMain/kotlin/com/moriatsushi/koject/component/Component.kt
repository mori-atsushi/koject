package com.moriatsushi.koject.component

import com.moriatsushi.koject.ExperimentalKojectApi

/**
 * Define component annotations
 *
 * The components can group and organize provided types.
 * A type belonging to a Component can only inject the same Component
 * or the Root Component.
 * Additional dependencies can be provided using [ComponentExtras].
 */
@ExperimentalKojectApi
@Target(AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
annotation class Component
