package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSDeclaration
import com.google.devtools.ksp.symbol.KSType
import com.moriatsushi.koject.processor.analytics.findArgumentByName

@JvmInline
internal value class Identifier(
    private val value: String,
) : Comparable<Identifier> {
    companion object {
        internal fun of(classDeclaration: KSClassDeclaration): Identifier {
            return Identifier(classDeclaration.fullName)
        }

        internal fun of(typeDeclaration: KSType, qualifier: KSAnnotation?): Identifier {
            val value = buildString {
                append(typeDeclaration.fullName)
                if (qualifier != null) {
                    append("-")
                    append(qualifier.fullName)
                }
            }
            return Identifier(value)
        }
    }

    /**
     * Name that can be used in code for functions, classes, etc.
     */
    fun asCodeName(): String {
        return value.replace(" ", "")
            .replace("-", "___")
            .replace(".", "_")
            .replace("<", "__")
            .replace(">", "__")
            .replace(",", "__")
            .replace("?", "_nullable")
    }

    /**
     * Alphabetical order
     */
    override fun compareTo(other: Identifier): Int {
        return value.compareTo(other.value)
    }

    override fun toString(): String {
        return value
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
