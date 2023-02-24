package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.moriatsushi.koject.internal.identifier.Identifier
import com.moriatsushi.koject.processor.identifier.of
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.ksp.toClassName

internal sealed interface ProviderDeclaration {
    val identifier: Identifier
    val dependencies: List<DependencyType>
    val containingFile: KSFile
    val typeName: TypeName

    companion object {
        fun of(node: KSAnnotated): ProviderDeclaration? {
            return when (node) {
                is KSClassDeclaration -> Class(node)
                is KSFunctionDeclaration -> Function(node)
                else -> null
            }
        }
    }

    class Class(private val ksClass: KSClassDeclaration) : ProviderDeclaration {
        override val identifier by lazy {
            Identifier.of(ksClass)
        }

        override val dependencies: List<DependencyType>
            get() = ksClass.primaryConstructor?.parameters
                .orEmpty()
                .map { DependencyType.of(it.type) }

        override val containingFile: KSFile
            get() = ksClass.containingFile!!

        override val typeName: TypeName
            get() = ksClass.toClassName()
    }

    class Function(
        private val ksFunction: KSFunctionDeclaration,
    ) : ProviderDeclaration {
        private val ksType = ksFunction.returnType!!.resolve()

        override val identifier: Identifier by lazy {
            Identifier.of(ksType)
        }

        override val dependencies: List<DependencyType>
            get() = ksFunction.parameters
                .map { DependencyType.of(it.type) }

        override val containingFile: KSFile
            get() = ksFunction.containingFile!!

        val memberName: MemberName
            get() = MemberName(
                ksFunction.packageName.asString(),
                ksFunction.simpleName.asString(),
            )

        override val typeName: TypeName
            get() = ksType.toClassName()
    }
}
