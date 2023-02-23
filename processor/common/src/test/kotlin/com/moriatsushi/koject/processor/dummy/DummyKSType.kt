package com.moriatsushi.koject.processor.dummy

import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSDeclaration
import com.google.devtools.ksp.symbol.KSType
import com.google.devtools.ksp.symbol.KSTypeArgument
import com.google.devtools.ksp.symbol.Nullability

class DummyKSType(
    override val annotations: Sequence<KSAnnotation>,
    override val arguments: List<KSTypeArgument>,
    override val declaration: KSDeclaration,
    override val isError: Boolean,
    override val isFunctionType: Boolean,
    override val isMarkedNullable: Boolean,
    override val isSuspendFunctionType: Boolean,
    override val nullability: Nullability,
) : KSType {
    class Builder(
        val declaration: KSDeclaration,
    ) {
        val annotations = mutableListOf<KSAnnotation>()
        val arguments = mutableListOf<KSTypeArgument>()
        var isError: Boolean = false
        var isFunctionType: Boolean = false
        var isMarkedNullable: Boolean = false
        var isSuspendFunctionType: Boolean = false
        var nullability: Nullability = Nullability.NOT_NULL

        fun build(): DummyKSType {
            return DummyKSType(
                annotations = annotations.asSequence(),
                arguments = arguments,
                declaration = declaration,
                isError = isError,
                isFunctionType = isFunctionType,
                isMarkedNullable = isMarkedNullable,
                isSuspendFunctionType = isSuspendFunctionType,
                nullability = nullability,
            )
        }
    }

    override fun isAssignableFrom(that: KSType): Boolean {
        TODO()
    }

    override fun isCovarianceFlexible(): Boolean {
        TODO()
    }

    override fun isMutabilityFlexible(): Boolean {
        TODO()
    }

    override fun makeNotNullable(): KSType {
        TODO()
    }

    override fun makeNullable(): KSType {
        TODO()
    }

    override fun replace(arguments: List<KSTypeArgument>): KSType {
        TODO()
    }

    override fun starProjection(): KSType {
        TODO()
    }
}

inline fun <reified T> dummyKSType(
    crossinline builder: DummyKSType.Builder.() -> Unit = {},
): DummyKSType {
    val declaration = dummyKSClassDeclaration<T>()
    return dummyKSType(declaration) {
        builder()
    }
}

fun dummyKSType(
    declaration: KSDeclaration,
    builder: DummyKSType.Builder.() -> Unit = {},
): DummyKSType {
    return DummyKSType.Builder(declaration)
        .apply { builder() }
        .build()
}
