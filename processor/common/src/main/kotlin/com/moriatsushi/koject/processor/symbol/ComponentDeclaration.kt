package com.moriatsushi.koject.processor.symbol

import com.moriatsushi.koject.internal.StringIdentifier
import com.moriatsushi.koject.processor.code.Names
import com.moriatsushi.koject.processor.code.escapedForCode
import com.squareup.kotlinpoet.ClassName

internal data class ComponentDeclaration(
    val name: ComponentName,
    val extrasHolder: ComponentExtrasHolderDeclaration?,
    private val factories: Sequence<FactoryDeclaration>,
) {
    val allFactories = factories.sortedBy { it.identifier.displayName }
    val normalFactories = allFactories.filter { !it.isSingleton }
    val singletonFactories = allFactories.filter { it.isSingleton }

    fun findExtraDependency(identifier: StringIdentifier): Dependency? {
        return extrasHolder?.extras?.find { it.identifier == identifier }
    }

    fun findFactory(identifier: StringIdentifier): FactoryDeclaration? {
        return factories.find { it.identifier == identifier }
    }

    companion object {
        fun create(
            factories: Sequence<FactoryDeclaration>,
            extrasHolder: ComponentExtrasHolderDeclaration,
        ): ComponentDeclaration {
            return ComponentDeclaration(
                name = extrasHolder.componentName,
                factories = factories,
                extrasHolder = extrasHolder,
            )
        }

        fun createRoot(
            factories: Sequence<FactoryDeclaration>,
        ): ComponentDeclaration {
            return ComponentDeclaration(
                name = ComponentName("RootComponent"),
                factories = factories,
                extrasHolder = null,
            )
        }
    }
}

internal val ComponentDeclaration.containerClassName: ClassName
    get() = ClassName(
        Names.generatedPackageName,
        "_${name.value.escapedForCode}_Container",
    )
