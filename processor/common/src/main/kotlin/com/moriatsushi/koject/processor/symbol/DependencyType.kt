package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSType
import com.google.devtools.ksp.symbol.KSValueParameter
import com.moriatsushi.koject.Named
import com.moriatsushi.koject.processor.analytics.findAnnotation
import com.moriatsushi.koject.processor.identifier.IdentifierFactory
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.ksp.toTypeName

internal class DependencyType(
    private val ksType: KSType,
    private val qualifier: KSAnnotation?,
) {
    companion object {
        fun of(node: KSValueParameter): DependencyType {
            val ksType = node.type.resolve()
            val qualifier = node.findAnnotation<Named>()
            return DependencyType(ksType, qualifier)
        }
    }

    val identifier by lazy {
        IdentifierFactory.create(ksType, qualifier)
    }

    fun asTypeName(): TypeName {
        return ksType.toTypeName()
    }
}
