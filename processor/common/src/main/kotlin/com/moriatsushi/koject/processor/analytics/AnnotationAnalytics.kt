package com.moriatsushi.koject.processor.analytics

import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSAnnotation
import com.moriatsushi.koject.internal.identifier.AssistantID
import com.moriatsushi.koject.internal.identifier.Identifier

internal fun KSAnnotated.findIdentifier(): Identifier? {
    return findAnnotation<AssistantID>()
        ?.findArgumentByName<String>("value")
        ?.run(::Identifier)
}

internal inline fun <reified T> KSAnnotated.findAnnotation(): KSAnnotation? {
    return annotations.find {
        it.shortName.asString() == T::class.simpleName &&
            it.annotationType.resolve().declaration.qualifiedName!!.asString() ==
            T::class.qualifiedName!!
    }
}

internal inline fun <reified T> KSAnnotation.findArgumentByName(
    name: String,
): T? {
    return arguments.find { it.name?.asString() == name }?.value as? T
}
