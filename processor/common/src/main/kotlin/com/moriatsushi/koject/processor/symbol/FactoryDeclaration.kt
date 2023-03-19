package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.moriatsushi.koject.internal.Location
import com.moriatsushi.koject.internal.StringIdentifier
import com.moriatsushi.koject.processor.code.AnnotationSpecFactory
import com.moriatsushi.koject.processor.code.Names
import com.moriatsushi.koject.processor.code.escapedForCode
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ksp.toClassName

internal data class FactoryDeclaration(
    val provided: Provided,
    val component: ComponentName?,
    val className: ClassName,
    val parameters: List<Dependency>,
    val copiedCount: Int,
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

    val copied: Boolean
        get() = copiedCount != 0

    fun copiedName(moduleName: String): ClassName {
        val simpleName = "_${moduleName.escapedForCode}_${className.simpleName}"
        return ClassName(className.packageName, simpleName)
    }

    fun createCopiedAnnotation(): AnnotationSpec {
        return AnnotationSpecFactory.createCopied(copiedCount + 1)
    }

    companion object
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
        copiedCount = ksClass.findCopiedCount(),
        containingFile = ksClass.containingFile,
    )
}

private val KSClassDeclaration.factoryParameters: List<Dependency>
    get() = primaryConstructor?.parameters
        .orEmpty()
        .map { Dependency.of(it) }
