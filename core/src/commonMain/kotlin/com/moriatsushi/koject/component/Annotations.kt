package com.moriatsushi.koject.component

import com.moriatsushi.koject.ExperimentalKojectApi
import kotlin.reflect.KClass

/**
 * Define component annotations
 */
@ExperimentalKojectApi
@Target(AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.BINARY)
annotation class Component

/**
 * Extras for creating the [Component]
 */
@ExperimentalKojectApi
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
annotation class ComponentExtras(val of: KClass<*>)
