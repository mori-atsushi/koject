package com.moriatsushi.koject.processor.identifier

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSDeclaration
import com.google.devtools.ksp.symbol.KSType
import com.moriatsushi.koject.internal.identifier.Identifier
import com.moriatsushi.koject.internal.identifier.TypeStruct

internal fun Identifier.Companion.of(classDeclaration: KSClassDeclaration): Identifier {
    val typeStruct = TypeStruct.of(classDeclaration)
    return of(typeStruct)
}

internal fun Identifier.Companion.of(type: KSType): Identifier {
    val typeStruct = TypeStruct.of(type)
    return of(typeStruct)
}

private fun TypeStruct.Companion.of(classDeclaration: KSClassDeclaration): TypeStruct {
    return TypeStruct(classDeclaration.fullName)
}

private fun TypeStruct.Companion.of(type: KSType): TypeStruct {
    val name = type.declaration.fullName
    val isNullable = type.isMarkedNullable
    val arguments = type.arguments
        .mapNotNull { it.type?.resolve() }
        .map { of(it) }

    return TypeStruct(
        name = name,
        isNullable = isNullable,
        arguments = arguments,
    )
}

private val KSDeclaration.fullName: String
    get() = (qualifiedName ?: simpleName).asString()
