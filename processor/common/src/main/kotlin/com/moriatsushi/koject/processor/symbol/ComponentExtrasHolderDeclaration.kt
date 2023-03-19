package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.moriatsushi.koject.internal.Location
import com.moriatsushi.koject.processor.code.AnnotationSpecFactory
import com.moriatsushi.koject.processor.code.Names
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName

internal class ComponentExtrasHolderDeclaration(
    val componentName: ComponentName,
    val extrasHolder: ExtrasHolderDeclaration,
    val copiedCount: Int,
) {
    val className: ClassName
        get() = extrasHolder.className
    val extras: Sequence<Provided>
        get() = extrasHolder.extras
    val location: Location
        get() = extrasHolder.location
    val containingFile: KSFile?
        get() = extrasHolder.containingFile
    val copied: Boolean
        get() = copiedCount != 0

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

internal fun Resolver.findComponentExtrasHolders(): Sequence<ComponentExtrasHolderDeclaration> {
    @OptIn(KspExperimental::class)
    return getDeclarationsFromPackage(Names.componentPackageName)
        .filterIsInstance<KSClassDeclaration>()
        .map { ComponentExtrasHolderDeclaration.of(it) }
}

internal fun ComponentExtrasHolderDeclaration.Companion.of(
    ksClass: KSClassDeclaration,
): ComponentExtrasHolderDeclaration {
    return ComponentExtrasHolderDeclaration(
        componentName = ksClass.findStringComponentName()!!,
        extrasHolder = ExtrasHolderDeclaration.of(ksClass),
        copiedCount = ksClass.findCopiedCount(),
    )
}
