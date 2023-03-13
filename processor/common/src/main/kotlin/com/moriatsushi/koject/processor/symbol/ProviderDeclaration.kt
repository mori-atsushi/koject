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
import com.moriatsushi.koject.processor.analytics.name
import com.moriatsushi.koject.processor.error.CodeGenerationException
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.MemberName.Companion.member
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.toTypeName

internal data class ProviderDeclaration(
    val name: ProviderName,
    val identifier: TypedIdentifier,
    val component: ComponentName?,
    val parameters: List<ProviderParameter>,
    val isSingleton: Boolean,
    val location: Location,
    val containingFile: KSFile?,
) {
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
    check(ksClass)

    val qualifier = ksClass.findQualifierAnnotation()
    val bindAnnotation = ksClass.findBindAnnotation()
    val typeName = bindAnnotation?.toTypeName
        ?: ksClass.toClassName()
    val name = ProviderName.Class(
        className = ksClass.toClassName(),
    )
    val primaryConstructor = ksClass.primaryConstructor
        ?: error("Not found primaryConstructor")

    return ProviderDeclaration(
        name = name,
        identifier = TypedIdentifier(typeName, qualifier),
        component = ksClass.findComponentName(),
        parameters = primaryConstructor.providerParameters,
        isSingleton = ksClass.isSingleton,
        location = ksClass.createLocationAnnotation(),
        containingFile = ksClass.containingFile,
    )
}

private fun check(ksClass: KSClassDeclaration) {
    when (ksClass.classKind) {
        ClassKind.INTERFACE -> {
            throw CodeGenerationException(
                "${ksClass.location.name}: " +
                    "Interface cannot be provided.",
            )
        }
        else -> {
            // available
        }
    }
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
    val name = ProviderName.Function(
        functionName = MemberName(
            ksFunction.packageName.asString(),
            ksFunction.simpleName.asString(),
        ),
    )
    return ProviderDeclaration(
        name = name,
        identifier = ksFunction.identifier,
        component = ksFunction.findComponentName(),
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
    val name = ProviderName.Function(
        objectName = objectName,
        functionName = functionName,
    )

    return ProviderDeclaration(
        name = name,
        identifier = ksFunction.identifier,
        component = ksFunction.findComponentName(),
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
