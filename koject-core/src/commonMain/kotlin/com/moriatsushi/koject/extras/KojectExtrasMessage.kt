package com.moriatsushi.koject.extras

import com.moriatsushi.koject.ExperimentalKojectApi

/**
 * Message displayed when the [KojectExtras] were not specified
 */
@ExperimentalKojectApi
@Target(AnnotationTarget.CLASS)
@MustBeDocumented
@Retention(AnnotationRetention.BINARY)
annotation class KojectExtrasMessage(val message: String)
