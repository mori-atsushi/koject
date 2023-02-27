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

internal data class QualifierAnnotation(
    val fullName: String,
    val newInstanceCode: CodeBlock,
) {
    companion object
}

internal fun KSAnnotated.findQualifierAnnotation(): QualifierAnnotation? {
    val annotation = annotations.find {
        val declaration = it.annotationType.resolve().declaration
        declaration.hasAnnotation<Qualifier>()
    } ?: return null

    return QualifierAnnotation.of(annotation)
}

private fun QualifierAnnotation.Companion.of(annotation: KSAnnotation): QualifierAnnotation {
    val newInstanceCode = annotation.toNewInstanceCode()
    val fullName = if (annotation.isInstance<Named>()) {
        val value = annotation.findArgumentByName<String>("name")
        "Named($value)"
    } else {
        newInstanceCode.toString()
            .removeSuffix("()")
            .replace("\"", "")
    }
    return QualifierAnnotation(fullName, newInstanceCode)
}
