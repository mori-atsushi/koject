package com.moriatsushi.koject.processor.code

import com.moriatsushi.koject.internal.identifier.AssistantID
import com.moriatsushi.koject.internal.identifier.Identifier
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName

internal object AnnotationSpecFactory {
    private val internalAnnotationName = ClassName(
        "com.moriatsushi.koject.internal",
        "InternalKojectApi",
    )
    private val optInAnnotationName = ClassName("kotlin", "OptIn")

    fun createAssistantID(identifier: Identifier): AnnotationSpec {
        return AnnotationSpec.builder(AssistantID::class).apply {
            addMember("%S", identifier.value)
        }.build()
    }

    fun createInternal(): AnnotationSpec {
        return AnnotationSpec.builder(internalAnnotationName).build()
    }

    fun createOptInInternal(): AnnotationSpec {
        return AnnotationSpec.builder(optInAnnotationName).apply {
            addMember("%T::class", internalAnnotationName)
        }.build()
    }
}
