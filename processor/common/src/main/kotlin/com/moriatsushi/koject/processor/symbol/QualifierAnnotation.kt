package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSAnnotation
import com.moriatsushi.koject.Named
import com.moriatsushi.koject.Qualifier
import com.moriatsushi.koject.processor.analytics.findArgumentByName
import com.moriatsushi.koject.processor.analytics.hasAnnotation
import com.moriatsushi.koject.processor.analytics.isInstance
import com.moriatsushi.koject.processor.code.toNewInstanceCode
import com.squareup.kotlinpoet.CodeBlock

internal class QualifierAnnotation(
    private val ksAnnotation: KSAnnotation,
) {
    companion object {
        fun ofOrNull(node: KSAnnotated): QualifierAnnotation? {
            val annotation = node.annotations.find {
                val declaration = it.annotationType.resolve().declaration
                declaration.hasAnnotation<Qualifier>()
            } ?: return null
            return QualifierAnnotation(annotation)
        }
    }

    val fullName: String
        get() = buildString {
            if (ksAnnotation.isInstance<Named>()) {
                val value = ksAnnotation.findArgumentByName<String>("name")
                return "Named($value)"
            }
            var code = newInstanceCode.toString()
            if (code.endsWith("()")) {
                code = code.dropLast(2)
            }
            return code.replace("\"", "")
        }

    val newInstanceCode: CodeBlock
        get() = ksAnnotation.toNewInstanceCode()
}
