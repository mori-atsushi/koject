package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSType
import com.google.devtools.ksp.symbol.KSValueParameter
import com.moriatsushi.koject.internal.identifier.Identifier
import com.moriatsushi.koject.processor.analytics.findName
import com.moriatsushi.koject.processor.identifier.of
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.ksp.toTypeName

internal class DependencyType(
    private val ksType: KSType,
    private val name: String?,
) {
    companion object {
        fun of(node: KSValueParameter): DependencyType {
            val ksType = node.type.resolve()
            val name = node.findName()
            return DependencyType(ksType, name)
        }
    }

    val identifier by lazy {
        Identifier.of(ksType, name)
    }

    fun asTypeName(): TypeName {
        return ksType.toTypeName()
    }
}
