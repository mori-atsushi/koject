package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSValueParameter
import com.moriatsushi.koject.internal.StringIdentifier
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.ksp.toTypeName

internal data class TypedIdentifier(
    val typeName: TypeName,
    val qualifier: QualifierAnnotation?,
) {
    fun asStringIdentifier(): StringIdentifier {
        return StringIdentifier(
            typeName.toString(),
            qualifier?.fullName ?: "",
        )
    }

    fun asAnnotationSpec(): AnnotationSpec {
        return asStringIdentifier().asAnnotationSpec()
    }

    val displayName: String
        get() = asStringIdentifier().displayName

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
