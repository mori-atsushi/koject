package com.moriatsushi.koject.component

import com.moriatsushi.koject.ExperimentalKojectApi

/**
 * Define component annotations
 */
@ExperimentalKojectApi
@Target(AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
annotation class Component
