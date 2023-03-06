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
    private val experimentalAnnotationName = ClassName(
        "com.moriatsushi.koject",
        "ExperimentalKojectApi",
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

    fun createExperimental(): AnnotationSpec {
        return AnnotationSpec.builder(experimentalAnnotationName)
            .build()
    }

    fun createOptInExperimental(): AnnotationSpec {
        return AnnotationSpec.builder(optInAnnotationName).apply {
            addMember("%T::class", experimentalAnnotationName)
        }.build()
    }

    fun createSingleton(): AnnotationSpec {
        return AnnotationSpec.builder(Singleton::class.asClassName())
            .build()
    }

    fun createSuppress(vararg names: String): AnnotationSpec {
        return AnnotationSpec.builder(Suppress::class.asClassName()).apply {
            names.forEach { addMember("%S", it) }
        }.build()
    }
}
