package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.google.devtools.ksp.symbol.KSValueParameter
import com.moriatsushi.koject.internal.Identifier
import com.moriatsushi.koject.internal.StringIdentifier
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asTypeName
import com.squareup.kotlinpoet.buildCodeBlock
import com.squareup.kotlinpoet.ksp.toTypeName

internal data class TypedIdentifier(
    val typeName: TypeName,
    val qualifier: QualifierAnnotation?,
) {
    fun asStringIdentifier(): StringIdentifier {
        return StringIdentifier.of(typeName, qualifier)
    }

    fun asAnnotationSpec(): AnnotationSpec {
        return asStringIdentifier().asAnnotationSpec()
    }

    companion object
}

internal fun TypedIdentifier.Companion.of(
    parameter: KSValueParameter,
): TypedIdentifier {
    val ksType = parameter.type.resolve()
    val typeName = ksType.toTypeName()
    val qualifier = parameter.findQualifierAnnotation()
    return TypedIdentifier(typeName, qualifier)
}

internal fun TypedIdentifier.Companion.of(
    parameter: KSPropertyDeclaration,
): TypedIdentifier {
    val ksType = parameter.type.resolve()
    val typeName = ksType.toTypeName()
    val qualifier = parameter.findQualifierAnnotation()
    return TypedIdentifier(typeName, qualifier)
}

/**
 * Identifier.of<Type>(Qualifier())
 */
internal val TypedIdentifier.newInstanceCode: CodeBlock
    get() = buildCodeBlock {
        add("%T.of<%T>(", Identifier::class.asTypeName(), typeName)
        val qualifier = qualifier
        if (qualifier != null) {
            add("\n")
            indent()
            add(qualifier.newInstanceCode)
            unindent()
            add("\n")
        }
        add(")")
    }
