package com.moriatsushi.koject.internal

import com.moriatsushi.koject.identifier.Identifier
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.typeOf

@PublishedApi
internal inline fun <reified T> Identifier.Companion.of(): Identifier {
    return of(TypeStruct.of<T>())
}

@PublishedApi
internal fun Identifier.Companion.of(typeStruct: TypeStruct): Identifier {
    val value = typeStruct.toString()
    return Identifier(value)
}

@PublishedApi
internal inline fun <reified T> TypeStruct.Companion.of(): TypeStruct {
    return of(typeOf<T>())
}

@PublishedApi
internal fun TypeStruct.Companion.of(type: KType): TypeStruct {
    val name = (type.classifier as KClass<*>).fullName
    val arguments = type.arguments
        .mapNotNull { it.type }
        .map { of(it) }

    return TypeStruct(
        name = name,
        arguments = arguments,
        isNullable = type.isMarkedNullable,
    )
}
