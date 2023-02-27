package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSType
import com.moriatsushi.koject.Binds
import com.moriatsushi.koject.processor.analytics.findAnnotation
import com.moriatsushi.koject.processor.analytics.findArgumentByName
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.ksp.toTypeName

@JvmInline
internal value class BindsAnnotation(
    val toTypeName: TypeName?,
) {
    companion object
}

internal fun KSClassDeclaration.findBindAnnotation(): BindsAnnotation? {
    val annotation = findAnnotation<Binds>() ?: return null
    val toType = annotation.findArgumentByName<KSType>("to")
    return BindsAnnotation(
        toTypeName = if (toType != null && !toType.isNothing) {
            toType.toTypeName()
        } else {
            null
        },
    )
}

private val KSType.isNothing: Boolean
    get() = declaration.qualifiedName?.asString() == Nothing::class.qualifiedName
