package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.moriatsushi.koject.internal.Copied
import com.moriatsushi.koject.internal.Location
import com.moriatsushi.koject.internal.StringIdentifier
import com.moriatsushi.koject.processor.analytics.findAnnotation
import com.moriatsushi.koject.processor.analytics.findArgumentByName
import com.moriatsushi.koject.processor.code.AnnotationSpecFactory
import com.moriatsushi.koject.processor.code.Names
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

    fun copiedName(): ClassName {
        val base = className.simpleName
        val matchResult = regex.find(base)
        val updated = if (matchResult != null) {
            base.substring(0, matchResult.range.first) + (copiedCount + 2)
        } else {
            base + "2"
        }
        return ClassName(className.packageName, updated)
    }

    fun createCopiedAnnotation(): AnnotationSpec {
        return AnnotationSpecFactory.createCopied(copiedCount + 1)
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
    val copiedCount = ksClass.findAnnotation<Copied>()
        ?.findArgumentByName<Int>("count") ?: 0

    return FactoryDeclaration(
        provided = Provided.of(ksClass),
        component = ksClass.findStringComponentName(),
        className = ksClass.toClassName(),
        parameters = ksClass.factoryParameters,
        copiedCount = copiedCount,
        containingFile = ksClass.containingFile,
    )
}

private val KSClassDeclaration.factoryParameters: List<Dependency>
    get() = primaryConstructor?.parameters
        .orEmpty()
        .map { Dependency.of(it) }
