package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.moriatsushi.koject.internal.identifier.Identifier
import com.moriatsushi.koject.processor.identifier.escapedValue
import com.moriatsushi.koject.processor.identifier.of
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ksp.toClassName

internal class ProviderDeclaration(
    private val ksClass: KSClassDeclaration,
) {
    companion object {
        fun of(node: KSAnnotated): ProviderDeclaration? {
            return when (node) {
                is KSClassDeclaration -> ProviderDeclaration(node)
                else -> null
            }
        }
    }

    val identifier by lazy {
        Identifier.of(ksClass)
    }

    val factoryName: String
        get() = "_${identifier.escapedValue}_Factory"

    val dependencies: List<DependencyType>
        get() = ksClass.primaryConstructor?.parameters
            .orEmpty()
            .map { DependencyType.of(it.type) }

    val containingFile: KSFile
        get() = ksClass.containingFile!!

    fun asTypeName(): ClassName {
        return ksClass.toClassName()
    }
}
