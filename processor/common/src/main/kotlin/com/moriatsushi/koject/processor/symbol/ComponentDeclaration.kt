package com.moriatsushi.koject.processor.symbol

import com.moriatsushi.koject.internal.StringIdentifier
import com.moriatsushi.koject.processor.code.Names
import com.moriatsushi.koject.processor.code.escapedForCode
import com.squareup.kotlinpoet.ClassName

internal sealed class ComponentDeclaration(
    private val factories: Sequence<FactoryDeclaration>,
) {
    abstract val name: ComponentName
    abstract val extrasHolders: Sequence<ExtrasHolderDeclaration>
    abstract val allProvided: Sequence<Provided>

    class Root(
        factories: Sequence<FactoryDeclaration>,
        override val extrasHolders: Sequence<ExtrasHolderDeclaration>,
    ) : ComponentDeclaration(factories) {
        override val name = ComponentName("RootComponent")

        override val allProvided: Sequence<Provided>
            get() = allFactories.map { it.provided } +
                extrasHolders.flatMap { it.extras }
    }

    class Child(
        factories: Sequence<FactoryDeclaration>,
        val extrasHolder: ComponentExtrasHolderDeclaration,
        val rootComponent: Root,
    ) : ComponentDeclaration(factories) {
        override val name = extrasHolder.componentName

        override val allProvided: Sequence<Provided>
            get() = allFactories.map { it.provided } +
                extrasHolder.extras +
                rootComponent.allProvided

        override val extrasHolders: Sequence<ExtrasHolderDeclaration>
            get() = sequenceOf(extrasHolder.extrasHolder)

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
