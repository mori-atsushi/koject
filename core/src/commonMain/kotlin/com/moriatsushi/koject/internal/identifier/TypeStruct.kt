package com.moriatsushi.koject.internal.identifier

import com.moriatsushi.koject.internal.InternalKojectApi
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.typeOf

@InternalKojectApi
data class TypeStruct(
    val name: String,
    val isNullable: Boolean = false,
    val arguments: List<TypeStruct> = emptyList(),
) {
    override fun toString(): String {
        return buildString {
            append(name)
            if (arguments.isNotEmpty()) {
                append("<")
                arguments.joinTo(this, ", ") {
                    it.toString()
                }
                append(">")
            }
            if (isNullable) {
                append("?")
            }
        }
    }

    companion object {
        @PublishedApi
        internal inline fun <reified T> of(): TypeStruct {
            return of(typeOf<T>())
        }

        @PublishedApi
        internal fun of(type: KType): TypeStruct {
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
    }
}
