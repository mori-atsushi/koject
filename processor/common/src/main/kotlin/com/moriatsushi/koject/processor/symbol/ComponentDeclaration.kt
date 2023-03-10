package com.moriatsushi.koject.processor.symbol

import com.moriatsushi.koject.internal.StringIdentifier
import com.moriatsushi.koject.processor.code.Names
import com.moriatsushi.koject.processor.code.escapedForCode
import com.squareup.kotlinpoet.ClassName

internal sealed class ComponentDeclaration(
    private val factories: Sequence<FactoryDeclaration>,
) {
    abstract val name: ComponentName

    class Root(
        factories: Sequence<FactoryDeclaration>,
        val extrasHolders: Sequence<ExtrasHolderDeclaration>,
    ) : ComponentDeclaration(factories) {
        override val name = ComponentName("RootComponent")

        val allProvided: Sequence<Provided>
            get() = allFactories.map { it.provided } +
                extrasHolders.flatMap { it.extras }
    }

    class Child(
        factories: Sequence<FactoryDeclaration>,
        val extrasHolder: ComponentExtrasHolderDeclaration,
    ) : ComponentDeclaration(factories) {
        override val name = extrasHolder.componentName

        val allProvided: Sequence<Provided>
            get() = allFactories.map { it.provided } +
                extrasHolder.extras

        fun findExtra(identifier: StringIdentifier): Provided? {
            return extrasHolder.extras.find {
                it.identifier == identifier
            }
        }
    }

    val allFactories = factories.sortedBy { it.identifier.displayName }
    val normalFactories = allFactories.filter { !it.isSingleton }
    val singletonFactories = allFactories.filter { it.isSingleton }

    fun findFactory(identifier: StringIdentifier): FactoryDeclaration? {
        return factories.find { it.identifier == identifier }
    }
}

internal val ComponentDeclaration.containerClassName: ClassName
    get() = ClassName(
        Names.generatedPackageName,
        "_${name.value.escapedForCode}_Container",
    )
