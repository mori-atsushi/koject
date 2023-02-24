package com.moriatsushi.koject.internal.identifier

import com.moriatsushi.koject.internal.InternalKojectApi

/**
 * Annotation used to hold [Identifier.value] for code generation
 */
@Suppress("ClassName")
@InternalKojectApi
@Retention(value = AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.VALUE_PARAMETER,
)
annotation class _Identifier(val value: String)
