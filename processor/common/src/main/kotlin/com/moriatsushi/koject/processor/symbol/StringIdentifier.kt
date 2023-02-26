package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSDeclaration
import com.google.devtools.ksp.symbol.KSType
import com.moriatsushi.koject.internal.identifier.StringIdentifier
import java.security.MessageDigest
import java.util.Base64

internal fun StringIdentifier.Companion.of(classDeclaration: KSClassDeclaration): StringIdentifier {
    return StringIdentifier(classDeclaration.fullName)
}

internal fun StringIdentifier.Companion.of(
    typeDeclaration: KSType,
    qualifier: QualifierAnnotation?,
): StringIdentifier {
    val value = buildString {
        append(typeDeclaration.fullName)
        if (qualifier != null) {
            append(":")
            append(qualifier.fullName)
        }
    }
    return StringIdentifier(value)
}

/**
 * Name that can be used in code for functions, classes, etc.
 */
internal fun StringIdentifier.asCodeName(): String {
    return buildString {
        val type = value.substringBefore(":")
        append(type.escaped)
        val qualifier = value.substringAfter(":", "")
        if (qualifier.isNotEmpty()) {
            append("__")
            append(qualifier.hash)
        }
    }
}

private val KSType.fullName: String
    get() = buildString {
        append(declaration.fullName)
        if (arguments.isNotEmpty()) {
            append("<")
            arguments.joinTo(this, ", ") {
                it.type?.resolve()?.fullName.toString()
            }
            append(">")
        }
        if (isMarkedNullable) {
            append("?")
        }
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

internal val KSDeclaration.fullName: String
    get() = (qualifiedName ?: simpleName).asString()
