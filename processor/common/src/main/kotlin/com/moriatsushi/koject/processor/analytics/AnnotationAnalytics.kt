package com.moriatsushi.koject.processor.analytics

import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSAnnotation

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
