package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.moriatsushi.koject.internal.Location
import com.moriatsushi.koject.internal.StringIdentifier
import com.moriatsushi.koject.processor.code.Names
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ksp.toClassName

internal data class FactoryDeclaration(
    val provided: Provided,
    val component: ComponentName?,
    val className: ClassName,
    val parameters: List<Dependency>,
    val containingFile: KSFile?,
) {
    val identifier: StringIdentifier
        get() = provided.identifier

    val isSingleton: Boolean
        get() = provided.isSingleton

    val forTest: Boolean
        get() = provided.forTest

    val location: Location
        get() = provided.location

    fun copiedName(): ClassName {
        val base = className.simpleName
        val matchResult = regex.find(base)
        val updated = if (matchResult != null) {
            val copiedCount = matchResult.value.toInt()
            base.substring(0, matchResult.range.first) + (copiedCount + 1)
        } else {
            base + "2"
        }
        return ClassName(className.packageName, updated)
    }

    companion object {
        private val regex = Regex("\\d+$")
    }
}

internal fun Resolver.findFactories(): Sequence<FactoryDeclaration> {
    @OptIn(KspExperimental::class)
    return getDeclarationsFromPackage(Names.factoryPackageName)
        .filterIsInstance<KSClassDeclaration>()
        .map { FactoryDeclaration.of(it) }
}

internal fun FactoryDeclaration.Companion.of(
    ksClass: KSClassDeclaration,
): FactoryDeclaration {
    return FactoryDeclaration(
        provided = Provided.of(ksClass),
        component = ksClass.findStringComponentName(),
        className = ksClass.toClassName(),
        parameters = ksClass.factoryParameters,
        containingFile = ksClass.containingFile,
    )
}

private val KSClassDeclaration.factoryParameters: List<Dependency>
    get() = primaryConstructor?.parameters
        .orEmpty()
        .map { Dependency.of(it) }
