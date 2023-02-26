package com.moriatsushi.koject.processor.analytics

import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSAnnotation
import com.moriatsushi.koject.Qualifier
import com.moriatsushi.koject.internal.identifier._Identifier
import com.moriatsushi.koject.processor.symbol.Identifier
import com.moriatsushi.koject.processor.symbol.QualifierAnnotation

internal fun KSAnnotated.findIdentifier(): Identifier? {
    return findAnnotation<_Identifier>()
        ?.findArgumentByName<String>("value")
        ?.let(::Identifier)
}

internal fun KSAnnotated.findQualifier(): QualifierAnnotation? {
    return annotations.find {
        val declaration = it.annotationType.resolve().declaration
        declaration.hasAnnotation<Qualifier>()
    }?.let(::QualifierAnnotation)
}

internal inline fun <reified T> KSAnnotated.findAnnotation(): KSAnnotation? {
    return annotations.find { it.isInstance<T>() }
}

internal inline fun <reified T> KSAnnotated.hasAnnotation(): Boolean {
    return annotations.any { it.isInstance<T>() }
}

internal inline fun <reified T> KSAnnotation.isInstance(): Boolean {
    if (shortName.asString() != T::class.simpleName) {
        return false
    }
    val qualifiedName = annotationType.resolve().declaration.qualifiedName!!
    return qualifiedName.asString() == T::class.qualifiedName!!
}

internal inline fun <reified T> KSAnnotation.findArgumentByName(
    name: String,
): T? {
    return arguments.find { it.name?.asString() == name }?.value as? T
}
