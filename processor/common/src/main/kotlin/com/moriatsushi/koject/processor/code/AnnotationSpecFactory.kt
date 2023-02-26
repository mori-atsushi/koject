package com.moriatsushi.koject.processor.code

import com.moriatsushi.koject.Singleton
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.asClassName

internal object AnnotationSpecFactory {
    private val internalAnnotationName = ClassName(
        "com.moriatsushi.koject.internal",
        "InternalKojectApi",
    )
    private val optInAnnotationName = ClassName("kotlin", "OptIn")

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
