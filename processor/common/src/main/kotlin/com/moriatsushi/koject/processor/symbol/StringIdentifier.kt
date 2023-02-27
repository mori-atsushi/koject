package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSAnnotated
import com.moriatsushi.koject.internal.identifier.StringIdentifier
import com.moriatsushi.koject.processor.analytics.findAnnotation
import com.moriatsushi.koject.processor.analytics.findArgumentByName
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asClassName
import java.security.MessageDigest
import java.util.Base64

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
        append(type.escaped)
        if (qualifier.isNotEmpty()) {
            append("__")
            append(qualifier.hash)
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

private val String.escaped: String
    get() = this
        .replace(" ", "")
        .replace(".", "_")
        .replace("<", "__")
        .replace(">", "__")
        .replace(",", "__")
        .replace("?", "_nullable")

private val String.hash: String
    get() {
        val sha256 = MessageDigest.getInstance("SHA-256").digest(this.toByteArray())
        return Base64.getUrlEncoder().withoutPadding()
            .encodeToString(sha256)
            .take(16)
            .replace("-", "_")
    }
