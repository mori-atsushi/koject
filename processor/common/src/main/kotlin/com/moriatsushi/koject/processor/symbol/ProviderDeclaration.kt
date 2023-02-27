package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.ClassKind
import com.google.devtools.ksp.symbol.FunctionKind
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.moriatsushi.koject.Binds
import com.moriatsushi.koject.Singleton
import com.moriatsushi.koject.internal.identifier.StringIdentifier
import com.moriatsushi.koject.processor.analytics.hasAnnotation
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.MemberName.Companion.member
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.toTypeName

internal sealed class ProviderDeclaration(
    private val declaration: KSDeclaration,
    private val function: KSFunctionDeclaration,
) {
    abstract val identifier: StringIdentifier
    abstract val typeName: TypeName
    abstract val qualifier: QualifierAnnotation?

    val dependencies: List<TypedIdentifier>
        get() = function.parameters
            .map { TypedIdentifier.of(it) }

    val containingFile: KSFile
        get() = declaration.containingFile!!

    val isSingleton: Boolean
        get() = declaration.hasAnnotation<Singleton>()

    companion object {
        fun of(node: KSAnnotated): ProviderDeclaration? {
            return when (node) {
                is KSClassDeclaration -> ofClass(node)
                is KSFunctionDeclaration -> ofFunction(node)
                else -> null
            }
        }

        private fun ofClass(node: KSClassDeclaration): ProviderDeclaration {
            return Class(node)
        }

        private fun ofFunction(node: KSFunctionDeclaration): ProviderDeclaration? {
            return when (node.functionKind) {
                FunctionKind.TOP_LEVEL -> TopLevelFunction(node)
                FunctionKind.MEMBER -> {
                    val parent = node.parentDeclaration as KSClassDeclaration
                    if (parent.classKind == ClassKind.OBJECT) {
                        ObjectFunction(parent, node)
                    } else {
                        null
                    }
                }
                else -> null
            }
        }
    }

    class Class(
        private val ksClass: KSClassDeclaration,
    ) : ProviderDeclaration(
        ksClass,
        ksClass.primaryConstructor!!,
    ) {
        private val hasBindsAnnotation = ksClass.hasAnnotation<Binds>()

        val className: ClassName
            get() = ksClass.toClassName()

        override val identifier by lazy {
            if (hasBindsAnnotation) {
                val type = ksClass.superTypes.first().resolve()
                StringIdentifier.of(type.toTypeName(), qualifier)
            } else {
                StringIdentifier.of(ksClass.toClassName())
            }
        }

        override val typeName: TypeName
            get() = if (hasBindsAnnotation) {
                val type = ksClass.superTypes.first().resolve()
                type.toTypeName()
            } else {
                ksClass.toClassName()
            }

        override val qualifier: QualifierAnnotation? = null
    }

    class TopLevelFunction(
        private val function: KSFunctionDeclaration,
    ) : ProviderDeclaration(function, function) {
        private val ksType = function.returnType!!.resolve()

        override val identifier by lazy {
            StringIdentifier.of(ksType.toTypeName(), qualifier)
        }

        val memberName: MemberName
            get() = MemberName(
                function.packageName.asString(),
                function.simpleName.asString(),
            )

        override val typeName: TypeName
            get() = ksType.toTypeName()

        override val qualifier by lazy {
            function.findQualifierAnnotation()
        }
    }

    class ObjectFunction(
        private val parent: KSClassDeclaration,
        private val function: KSFunctionDeclaration,
    ) : ProviderDeclaration(function, function) {
        private val ksType = function.returnType!!.resolve()

        override val identifier: StringIdentifier by lazy {
            StringIdentifier.of(ksType.toTypeName(), qualifier)
        }

        val parentName: ClassName
            get() = parent.toClassName()

        val memberName: MemberName
            get() = parentName.member(function.simpleName.asString())

        override val typeName: TypeName
            get() = ksType.toTypeName()

        override val qualifier by lazy {
            function.findQualifierAnnotation()
        }
    }
}
