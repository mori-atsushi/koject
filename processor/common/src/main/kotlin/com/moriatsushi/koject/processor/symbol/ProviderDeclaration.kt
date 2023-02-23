package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.moriatsushi.koject.internal.identifier.Identifier
import com.moriatsushi.koject.processor.identifier.escapedValue
import com.moriatsushi.koject.processor.identifier.of

internal class ProviderDeclaration(
    val ksClass: KSClassDeclaration,
) {
    companion object {
        fun of(node: KSAnnotated): ProviderDeclaration? {
            return when (node) {
                is KSClassDeclaration -> ProviderDeclaration(node)
                else -> null
            }
        }
    }

    private val identifier by lazy {
        Identifier.of(ksClass)
    }

    val factoryName: String
        get() = "_${identifier.escapedValue}_Factory"
}
