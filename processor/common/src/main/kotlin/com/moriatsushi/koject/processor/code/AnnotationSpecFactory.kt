package com.moriatsushi.koject.processor.code

import com.moriatsushi.koject.Singleton
import com.moriatsushi.koject.internal.identifier.StringIdentifier
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.asClassName

internal object AnnotationSpecFactory {
    private val identifierAnnotationName = ClassName(
        "com.moriatsushi.koject.internal.identifier",
        "StringIdentifier",
    )
    private val internalAnnotationName = ClassName(
        "com.moriatsushi.koject.internal",
        "InternalKojectApi",
    )
    private val optInAnnotationName = ClassName("kotlin", "OptIn")

    fun createIdentifier(identifier: StringIdentifier): AnnotationSpec {
        return AnnotationSpec.builder(identifierAnnotationName).apply {
            addMember("%S", identifier.value)
        }.build()
    }

    fun createInternal(): AnnotationSpec {
        return AnnotationSpec.builder(internalAnnotationName)
            .build()
    }

    fun createOptInInternal(): AnnotationSpec {
        return AnnotationSpec.builder(optInAnnotationName).apply {
            addMember("%T::class", internalAnnotationName)
        }.build()
    }

    fun createSingleton(): AnnotationSpec {
        return AnnotationSpec.builder(Singleton::class.asClassName())
            .build()
    }
}
