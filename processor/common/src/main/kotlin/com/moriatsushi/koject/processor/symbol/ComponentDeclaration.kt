package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.isPrivate
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.moriatsushi.koject.internal.StringIdentifier
import com.moriatsushi.koject.processor.code.Names
import com.moriatsushi.koject.processor.code.escapedForCode
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ksp.toClassName

internal data class ComponentDeclaration(
    val name: ComponentName,
    val className: ClassName?,
    val extras: Sequence<Dependency>,
    private val factories: Sequence<FactoryDeclaration>,
    val containingFile: KSFile?,
) {
    val allFactories = factories.sortedBy { it.identifier.displayName }
    val normalFactories = allFactories.filter { !it.isSingleton }
    val singletonFactories = allFactories.filter { it.isSingleton }

    fun findExtraDependency(identifier: StringIdentifier): Dependency? {
        return extras.find { it.identifier == identifier }
    }

    fun findFactory(identifier: StringIdentifier): FactoryDeclaration? {
        return factories.find { it.identifier == identifier }
    }

    companion object {
        fun createRoot(
            factories: Sequence<FactoryDeclaration>,
        ): ComponentDeclaration {
            return ComponentDeclaration(
                name = ComponentName("RootComponent"),
                className = null,
                extras = emptySequence(),
                factories = factories,
                containingFile = null,
            )
        }
    }
}

internal val ComponentDeclaration.containerClassName: ClassName
    get() = ClassName(
        Names.generatedPackageName,
        "_${name.value.escapedForCode}_Container",
    )

internal fun ComponentDeclaration.Companion.of(
    ksClass: KSClassDeclaration,
    factories: Sequence<FactoryDeclaration>,
): ComponentDeclaration {
    val name = ksClass.findStringComponentName()!!

    return ComponentDeclaration(
        name = name,
        className = ksClass.toClassName(),
        extras = ksClass.extraParameters,
        factories = factories.filter { it.component == name },
        containingFile = ksClass.containingFile,
    )
}

private val KSClassDeclaration.extraParameters: Sequence<Dependency>
    get() = getAllProperties()
        .filterNot { it.isPrivate() }
        .map { Dependency.of(it) }
