package com.moriatsushi.koject.processor.code

import com.google.devtools.ksp.symbol.ClassKind
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSName
import com.google.devtools.ksp.symbol.KSType
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.buildCodeBlock
import com.squareup.kotlinpoet.ksp.toAnnotationSpec
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.toTypeName

internal fun KSAnnotation.toNewInstanceCode(): CodeBlock {
    val type = annotationType.resolve()
    return buildCodeBlock {
        add("%T(", type.toTypeName())
        arguments.forEachIndexed { index, argument ->
            if (index > 0) {
                add(", ")
            }
            addValueToBlock(argument.value!!)
        }
        add(")")
    }
}

private fun CodeBlock.Builder.addValueToBlock(value: Any) {
    when (value) {
        is List<*> -> {
            // Array type
            add("arrayOf(")
            value.forEachIndexed { index, innerValue ->
                if (index > 0) add(", ")
                addValueToBlock(innerValue!!)
            }
            add(")")
        }
        is KSType -> {
            val isEnum =
                (value.declaration as KSClassDeclaration).classKind == ClassKind.ENUM_ENTRY
            if (isEnum) {
                val parent = value.declaration.parentDeclaration as KSClassDeclaration
                val entry = value.declaration.simpleName.getShortName()
                add("%T.%L", parent.toClassName(), entry)
            } else {
                add("%T::class", value.toClassName())
            }
        }
        is KSName ->
            add(
                "%T.%L",
                ClassName.bestGuess(value.getQualifier()),
                value.getShortName(),
            )
        is KSAnnotation -> add("%L", value.toAnnotationSpec())
        else -> add(memberForValue(value))
    }
}

private fun memberForValue(value: Any) = when (value) {
    is Class<*> -> CodeBlock.of("%T::class", value)
    is Enum<*> -> CodeBlock.of("%T.%L", value.javaClass, value.name)
    is String -> CodeBlock.of("%S", value)
    is Float -> CodeBlock.of("%Lf", value)
    is Double -> CodeBlock.of("%L", value)
    is Char -> CodeBlock.of("$value.toChar()")
    is Byte -> CodeBlock.of("$value.toByte()")
    is Short -> CodeBlock.of("$value.toShort()")
    is Int, is Boolean -> CodeBlock.of("%L", value)
    else -> error("unsupported value: $value")
}
