package com.moriatsushi.koject.processor.identifier

import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSDeclaration
import com.google.devtools.ksp.symbol.KSType
import com.moriatsushi.koject.processor.analytics.findArgumentByName

object IdentifierFactory {
    fun create(classDeclaration: KSClassDeclaration): String {
        return classDeclaration.fullName
    }

    fun create(typeDeclaration: KSType, qualifier: KSAnnotation?): String {
        return buildString {
            append(typeDeclaration.fullName)
            if (qualifier != null) {
                append("-")
                append(qualifier.fullName)
            }
        }
    }

    private val KSDeclaration.fullName: String
        get() = (qualifiedName ?: simpleName).asString()

    private val KSType.fullName: String
        get() = buildString {
            append(declaration.fullName)
            if (arguments.isNotEmpty()) {
                append("<")
                arguments.joinTo(this, ", ") {
                    it.toString()
                }
                append(">")
            }
            if (isMarkedNullable) {
                append("?")
            }
        }

    private val KSAnnotation.fullName: String
        get() = findArgumentByName<String>("name").orEmpty()
}
