package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSAnnotation
import com.moriatsushi.koject.Named
import com.moriatsushi.koject.processor.analytics.findArgumentByName
import com.moriatsushi.koject.processor.analytics.isInstance
import com.moriatsushi.koject.processor.code.toNewInstanceCode
import com.squareup.kotlinpoet.CodeBlock

internal class QualifierAnnotation(
    private val ksAnnotation: KSAnnotation,
) {

    val fullName: String
        get() = buildString {
            if (ksAnnotation.isInstance<Named>()) {
                val value = ksAnnotation.findArgumentByName<String>("name")
                return "Named($value)"
            }
            return newInstanceCode.toString()
                .replace("\"", "")
        }

    val newInstanceCode: CodeBlock
        get() = ksAnnotation.toNewInstanceCode()
}
