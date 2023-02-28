package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.ClassKind
import com.google.devtools.ksp.symbol.FunctionKind
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.moriatsushi.koject.Singleton
import com.moriatsushi.koject.internal.Location
import com.moriatsushi.koject.processor.analytics.hasAnnotation
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.MemberName.Companion.member
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.toTypeName

internal sealed interface ProviderDeclaration {
    val identifier: TypedIdentifier
    val parameters: List<ProviderParameter>
    val isSingleton: Boolean
    val location: Location
    val containingFile: KSFile?

    data class Class(
        val className: ClassName,
        override val identifier: TypedIdentifier,
        override val parameters: List<ProviderParameter>,
        override val isSingleton: Boolean,
        override val location: Location,
        override val containingFile: KSFile?,
    ) : ProviderDeclaration

    data class TopLevelFunction(
        val functionName: MemberName,
        override val identifier: TypedIdentifier,
        override val parameters: List<ProviderParameter>,
        override val isSingleton: Boolean,
        override val location: Location,
        override val containingFile: KSFile?,
    ) : ProviderDeclaration

    data class ObjectFunction(
        val objectName: ClassName,
        val functionName: MemberName,
        override val identifier: TypedIdentifier,
        override val parameters: List<ProviderParameter>,
        override val isSingleton: Boolean,
        override val location: Location,
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
    val qualifier = ksClass.findQualifierAnnotation()
    val bindAnnotation = ksClass.findBindAnnotation()
    val typeName = bindAnnotation?.toTypeName
        ?: ksClass.toClassName()

    return ProviderDeclaration.Class(
        className = ksClass.toClassName(),
        identifier = TypedIdentifier(typeName, qualifier),
        parameters = ksClass.primaryConstructor!!.providerParameters,
        isSingleton = ksClass.isSingleton,
        location = ksClass.createLocationAnnotation(),
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
        parameters = ksFunction.providerParameters,
        isSingleton = ksFunction.isSingleton,
        location = ksFunction.createLocationAnnotation(),
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
        parameters = ksFunction.providerParameters,
        isSingleton = ksFunction.isSingleton,
        location = ksFunction.createLocationAnnotation(),
        containingFile = ksFunction.containingFile,
    )
}

private val KSFunctionDeclaration.providerParameters: List<ProviderParameter>
    get() = parameters.map {
        ProviderParameter(
            identifier = TypedIdentifier.of(it),
            location = it.createLocationAnnotation(),
        )
    }

private val KSFunctionDeclaration.identifier: TypedIdentifier
    get() {
        val typeName = returnType!!.toTypeName()
        val qualifier = findQualifierAnnotation()
        return TypedIdentifier(typeName, qualifier)
    }

private val KSDeclaration.isSingleton: Boolean
    get() = hasAnnotation<Singleton>()
