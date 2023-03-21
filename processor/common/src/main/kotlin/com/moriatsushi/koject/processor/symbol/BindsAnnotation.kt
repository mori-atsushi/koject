package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSType
import com.moriatsushi.koject.Binds
import com.moriatsushi.koject.processor.analytics.findAnnotation
import com.moriatsushi.koject.processor.analytics.findArgumentByName
import com.moriatsushi.koject.processor.analytics.hasSuperType
import com.moriatsushi.koject.processor.analytics.isNothing
import com.moriatsushi.koject.processor.analytics.name
import com.moriatsushi.koject.processor.analytics.primarySuperType
import com.moriatsushi.koject.processor.error.CodeGenerationException
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
    val toType = if (argument == null || argument.isNothing) {
        primarySuperType ?: throwNotFoundSuperTypeException(this)
    } else {
        if (hasSuperType(argument)) {
            argument
        } else {
            throwNotIncludedSuperTypeException(this, argument)
        }
    }
    return BindsAnnotation(
        toTypeName = toType.toTypeName(),
    )
}

private fun throwNotFoundSuperTypeException(
    declaration: KSClassDeclaration,
): Nothing {
    val location = declaration.location.name
    val qualifiedName = declaration.qualifiedName?.asString()
    throw CodeGenerationException(
        "$location: $qualifiedName super type not found.",
    )
}

private fun throwNotIncludedSuperTypeException(
    declaration: KSClassDeclaration,
    argument: KSType,
): Nothing {
    val location = declaration.location.name
    val qualifiedName = declaration.qualifiedName?.asString()
    val argumentName = argument.declaration.qualifiedName?.asString()
    throw CodeGenerationException(
        "$location: " +
            "$argumentName is not included in the super types of $qualifiedName.",
    )
}
