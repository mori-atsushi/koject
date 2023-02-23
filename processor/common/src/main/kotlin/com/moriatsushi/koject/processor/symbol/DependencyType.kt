package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSType
import com.google.devtools.ksp.symbol.KSTypeReference
import com.moriatsushi.koject.internal.identifier.Identifier
import com.moriatsushi.koject.processor.identifier.of
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.ksp.toTypeName

internal class DependencyType(
    private val ksType: KSType,
) {
    companion object {
        fun of(node: KSTypeReference): DependencyType {
            return DependencyType(node.resolve())
        }
    }

    val identifier by lazy {
        Identifier.of(ksType)
    }

    fun asTypeName(): TypeName {
        return ksType.toTypeName()
    }
}