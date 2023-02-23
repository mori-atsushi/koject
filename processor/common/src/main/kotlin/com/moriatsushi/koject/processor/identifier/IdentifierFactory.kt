package com.moriatsushi.koject.processor.identifier

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSDeclaration
import com.moriatsushi.koject.internal.identifier.Identifier
import com.moriatsushi.koject.internal.identifier.TypeStruct

internal fun Identifier.Companion.of(classDeclaration: KSClassDeclaration): Identifier {
    val typeStruct = TypeStruct.of(classDeclaration)
    return of(typeStruct)
}

private fun TypeStruct.Companion.of(classDeclaration: KSClassDeclaration): TypeStruct {
    return TypeStruct(classDeclaration.fullName)
}

private val KSDeclaration.fullName: String
    get() = (qualifiedName ?: simpleName).asString()
