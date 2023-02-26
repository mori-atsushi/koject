package com.moriatsushi.koject.processor.code

import com.google.devtools.ksp.symbol.ClassKind
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSName
import com.google.devtools.ksp.symbol.KSType
import com.moriatsushi.koject.processor.error.CodeGenerationException
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
            val value = argument.value!!
            val block = valueCodeBlock(value)
            if (block != null) {
                add(block)
            } else {
                throw CodeGenerationException(
                    """
                    |${value::class.qualifiedName} is an unsupported annotation member type.",
                    |at ${type.declaration.qualifiedName!!.asString()}.${argument.name!!.asString()}
                    |
                    """.trimMargin(),
                )
            }
        }
        add(")")
    }
}

private fun valueCodeBlock(value: Any): CodeBlock? {
    return when (value) {
        is KSType -> {
            val isEnum =
                (value.declaration as KSClassDeclaration).classKind == ClassKind.ENUM_ENTRY
            if (isEnum) {
                val parent = value.declaration.parentDeclaration as KSClassDeclaration
                val entry = value.declaration.simpleName.getShortName()
                CodeBlock.of("%T.%L", parent.toClassName(), entry)
            } else {
                CodeBlock.of("%T::class", value.toClassName())
            }
        }
        is KSName ->
            CodeBlock.of(
                "%T.%L",
                ClassName.bestGuess(value.getQualifier()),
                value.getShortName(),
            )
        is KSAnnotation -> CodeBlock.of("%L", value.toAnnotationSpec())
        is Class<*> -> CodeBlock.of("%T::class", value)
        is Enum<*> -> CodeBlock.of("%T.%L", value.javaClass, value.name)
        is String -> CodeBlock.of("%S", value)
        is Float -> CodeBlock.of("%Lf", value)
        is Double -> CodeBlock.of("%L", value)
        is Char -> CodeBlock.of("$value.toChar()")
        is Byte -> CodeBlock.of("$value.toByte()")
        is Short -> CodeBlock.of("$value.toShort()")
        is Int, is Boolean -> CodeBlock.of("%L", value)
        else -> null
    }
}
