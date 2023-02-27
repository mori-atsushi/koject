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
    val toTypeName: TypeName,
) {
    companion object
}

internal fun KSClassDeclaration.findBindAnnotation(): BindsAnnotation? {
    val annotation = findAnnotation<Binds>() ?: return null
    val argument = annotation.findArgumentByName<KSType>("to")
    val toType = if (argument != null && !argument.isNothing) {
        argument
    } else {
        superTypes.first().resolve()
    }
    return BindsAnnotation(
        toTypeName = toType.toTypeName(),
    )
}

private val KSType.isNothing: Boolean
    get() = declaration.qualifiedName?.asString() == Nothing::class.qualifiedName
