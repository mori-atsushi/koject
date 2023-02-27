package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.ClassKind
import com.google.devtools.ksp.symbol.FunctionKind
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.moriatsushi.koject.Singleton
import com.moriatsushi.koject.processor.analytics.hasAnnotation
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.MemberName.Companion.member
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.toTypeName

internal sealed interface ProviderDeclaration {
    val identifier: TypedIdentifier
    val dependencies: List<TypedIdentifier>
    val isSingleton: Boolean
    val containingFile: KSFile?

    data class Class(
        val className: ClassName,
        override val identifier: TypedIdentifier,
        override val dependencies: List<TypedIdentifier>,
        override val isSingleton: Boolean,
        override val containingFile: KSFile?,
    ) : ProviderDeclaration

    data class TopLevelFunction(
        val functionName: MemberName,
        override val identifier: TypedIdentifier,
        override val dependencies: List<TypedIdentifier>,
        override val isSingleton: Boolean,
        override val containingFile: KSFile?,
    ) : ProviderDeclaration

    data class ObjectFunction(
        val objectName: ClassName,
        val functionName: MemberName,
        override val identifier: TypedIdentifier,
        override val dependencies: List<TypedIdentifier>,
        override val isSingleton: Boolean,
        override val containingFile: KSFile?,
    ) : ProviderDeclaration

    companion object
}

internal fun ProviderDeclaration.Companion.of(
    node: KSAnnotated,
): ProviderDeclaration? {
    return when (node) {
        is KSClassDeclaration -> of(node)
        is KSFunctionDeclaration -> of(node)
        else -> null
    }
}

private fun ProviderDeclaration.Companion.of(
    ksClass: KSClassDeclaration,
): ProviderDeclaration {
    val bindAnnotation = ksClass.findBindAnnotation()
    val typeName = if (bindAnnotation != null) {
        bindAnnotation.toTypeName ?: run {
            val firstType = ksClass.superTypes.first().resolve()
            firstType.toTypeName()
        }
    } else {
        ksClass.toClassName()
    }

    return ProviderDeclaration.Class(
        className = ksClass.toClassName(),
        identifier = TypedIdentifier(typeName, null),
        dependencies = ksClass.primaryConstructor!!.dependencies,
        isSingleton = ksClass.isSingleton,
        containingFile = ksClass.containingFile,
    )
}

private fun ProviderDeclaration.Companion.of(
    ksFunction: KSFunctionDeclaration,
): ProviderDeclaration? {
    return when (ksFunction.functionKind) {
        FunctionKind.TOP_LEVEL -> createTopLevelFunction(ksFunction)
        FunctionKind.MEMBER -> {
            val parent = ksFunction.parentDeclaration as KSClassDeclaration
            if (parent.classKind == ClassKind.OBJECT) {
                createObjectFunction(parent, ksFunction)
            } else {
                null
            }
        }
        else -> null
    }
}

private fun ProviderDeclaration.Companion.createTopLevelFunction(
    ksFunction: KSFunctionDeclaration,
): ProviderDeclaration {
    return ProviderDeclaration.TopLevelFunction(
        functionName = MemberName(
            ksFunction.packageName.asString(),
            ksFunction.simpleName.asString(),
        ),
        identifier = ksFunction.identifier,
        dependencies = ksFunction.dependencies,
        isSingleton = ksFunction.isSingleton,
        containingFile = ksFunction.containingFile,
    )
}

private fun ProviderDeclaration.Companion.createObjectFunction(
    parent: KSClassDeclaration,
    ksFunction: KSFunctionDeclaration,
): ProviderDeclaration {
    val objectName = parent.toClassName()
    val functionName = objectName.member(ksFunction.simpleName.asString())

    return ProviderDeclaration.ObjectFunction(
        objectName = objectName,
        functionName = functionName,
        identifier = ksFunction.identifier,
        dependencies = ksFunction.dependencies,
        isSingleton = ksFunction.isSingleton,
        containingFile = ksFunction.containingFile,
    )
}

private val KSFunctionDeclaration.dependencies: List<TypedIdentifier>
    get() = parameters.map { TypedIdentifier.of(it) }

private val KSFunctionDeclaration.identifier: TypedIdentifier
    get() {
        val typeName = returnType!!.toTypeName()
        val qualifier = findQualifierAnnotation()
        return TypedIdentifier(typeName, qualifier)
    }

private val KSDeclaration.isSingleton: Boolean
    get() = hasAnnotation<Singleton>()
