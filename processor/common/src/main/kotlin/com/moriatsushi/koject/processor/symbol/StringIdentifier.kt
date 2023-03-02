package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSAnnotated
import com.moriatsushi.koject.internal.StringIdentifier
import com.moriatsushi.koject.processor.analytics.findAnnotation
import com.moriatsushi.koject.processor.analytics.findArgumentByName
import com.moriatsushi.koject.processor.code.escapedForCode
import com.moriatsushi.koject.processor.code.hashForCode
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asClassName

internal fun StringIdentifier.Companion.of(
    typeName: TypeName,
    qualifier: QualifierAnnotation? = null,
): StringIdentifier {
    return StringIdentifier(
        typeName.toString(),
        qualifier?.fullName ?: "",
    )
}

fun KSAnnotated.findStringIdentifier(): StringIdentifier? {
    val annotation = this.findAnnotation<StringIdentifier>() ?: return null
    val type = annotation.findArgumentByName<String>("type")!!
    val qualifier = annotation.findArgumentByName<String>("qualifier").orEmpty()
    return StringIdentifier(type, qualifier)
}

/**
 * Name that can be used in code for functions, classes, etc.
 */
internal fun StringIdentifier.asCodeName(): String {
    return buildString {
        append(type.escapedForCode)
        if (qualifier.isNotEmpty()) {
            append("__")
            append(qualifier.hashForCode)
        }
    }
}

internal val StringIdentifier.displayName: String
    get() = buildString {
        append(type)
        if (qualifier.isNotEmpty()) {
            append("@")
            append(qualifier)
        }
    }

internal fun StringIdentifier.asAnnotationSpec(): AnnotationSpec {
    return AnnotationSpec.builder(StringIdentifier::class.asClassName()).apply {
        addMember("%S", type)
        if (qualifier.isNotEmpty()) {
            addMember("%S", qualifier)
        }
    }.build()
}
