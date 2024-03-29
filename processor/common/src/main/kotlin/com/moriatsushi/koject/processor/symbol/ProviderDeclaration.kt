package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.isAbstract
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.ClassKind
import com.google.devtools.ksp.symbol.FunctionKind
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Singleton
import com.moriatsushi.koject.internal.Location
import com.moriatsushi.koject.processor.analytics.hasAnnotation
import com.moriatsushi.koject.processor.analytics.name
import com.moriatsushi.koject.processor.code.escapedForCode
import com.moriatsushi.koject.processor.code.hashForCode
import com.moriatsushi.koject.processor.error.CodeGenerationException
import com.moriatsushi.koject.test.TestProvides
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.MemberName.Companion.member
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.toTypeName

internal data class ProviderDeclaration(
    val name: ProviderName,
    val identifier: TypedIdentifier,
    val component: ComponentName?,
    val parameters: List<ProviderParameter>?,
    val isSingleton: Boolean,
    val forTest: Boolean,
    val location: Location,
    val containingFile: KSFile?,
) {
    val factoryName: String
        get() {
            val typeName = identifier.typeName.toString()
            val qualifierName = identifier.qualifier?.fullName
            val declarationName = when (name) {
                is ProviderName.Class -> {
                    name.className.canonicalName
                }
                is ProviderName.Function -> {
                    name.functionName.canonicalName
                }
            }
            val extraName = buildString {
                if (qualifierName != null) {
                    append(qualifierName)
                }
                if (typeName != declarationName) {
                    if (isNotEmpty()) {
                        append("__")
                    }
                    append(declarationName)
                }
            }
            return buildString {
                append("_")
                append(typeName.escapedForCode)
                if (extraName.isNotEmpty()) {
                    append("__")
                    append(extraName.hashForCode)
                }
                append("_Factory")
            }
        }

    companion object
}

internal fun Resolver.findProviders(): Sequence<ProviderDeclaration> {
    val providers = getSymbolsWithAnnotation(Provides::class.qualifiedName!!)
        .map { ProviderDeclaration.of(it) }
    val testProviders = getSymbolsWithAnnotation(TestProvides::class.qualifiedName!!)
        .map { ProviderDeclaration.of(it, forTest = true) }
    return providers + testProviders
}

internal fun ProviderDeclaration.Companion.of(
    node: KSAnnotated,
    forTest: Boolean = false,
): ProviderDeclaration {
    return when (node) {
        is KSClassDeclaration -> of(node, forTest)
        is KSFunctionDeclaration -> of(node, forTest)
        else -> error("Not supported type: $node")
    }
}

private fun ProviderDeclaration.Companion.of(
    ksClass: KSClassDeclaration,
    forTest: Boolean,
): ProviderDeclaration {
    check(ksClass)

    val qualifier = ksClass.findQualifierAnnotation()
    val bindAnnotation = ksClass.findBindAnnotation()
    val typeName = bindAnnotation?.toTypeName
        ?: ksClass.toClassName()
    val name = ProviderName.Class(
        className = ksClass.toClassName(),
    )
    val parameters = ksClass.primaryConstructor?.providerParameters

    return ProviderDeclaration(
        name = name,
        identifier = TypedIdentifier(typeName, qualifier),
        component = ksClass.findComponentName(),
        parameters = parameters,
        isSingleton = ksClass.isSingleton,
        location = ksClass.createLocationAnnotation(),
        containingFile = ksClass.containingFile,
        forTest = forTest,
    )
}

private fun check(ksClass: KSClassDeclaration) {
    when {
        ksClass.classKind == ClassKind.INTERFACE -> {
            throw CodeGenerationException(
                "${ksClass.location.name}: " +
                    "Interface cannot be provided.",
            )
        }
        ksClass.classKind == ClassKind.ENUM_CLASS -> {
            throw CodeGenerationException(
                "${ksClass.location.name}: " +
                    "Enum class cannot be provided.",
            )
        }
        ksClass.isAbstract() -> {
            throw CodeGenerationException(
                "${ksClass.location.name}: " +
                    "Abstract class cannot be provided.",
            )
        }
        ksClass.typeParameters.isNotEmpty() -> {
            throw CodeGenerationException(
                "${ksClass.location.name}: " +
                    "Generics class cannot be provided directly. Please use provide function.",
            )
        }
        else -> {
            // available
        }
    }
}

private fun ProviderDeclaration.Companion.of(
    ksFunction: KSFunctionDeclaration,
    forTest: Boolean,
): ProviderDeclaration {
    val declaration = when (ksFunction.functionKind) {
        FunctionKind.TOP_LEVEL -> createTopLevelFunction(ksFunction, forTest)
        FunctionKind.MEMBER -> {
            val parent = ksFunction.parentDeclaration as KSClassDeclaration
            if (parent.classKind == ClassKind.OBJECT) {
                createObjectFunction(parent, ksFunction, forTest)
            } else {
                null
            }
        }
        else -> null
    }
    return declaration ?: throw CodeGenerationException(
        "${ksFunction.location.name}: " +
            "Provide by function is only allowed for top-level functions or object functions.",
    )
}

private fun ProviderDeclaration.Companion.createTopLevelFunction(
    ksFunction: KSFunctionDeclaration,
    forTest: Boolean,
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
        forTest = forTest,
        location = ksFunction.createLocationAnnotation(),
        containingFile = ksFunction.containingFile,
    )
}

private fun ProviderDeclaration.Companion.createObjectFunction(
    parent: KSClassDeclaration,
    ksFunction: KSFunctionDeclaration,
    forTest: Boolean,
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
        forTest = forTest,
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
