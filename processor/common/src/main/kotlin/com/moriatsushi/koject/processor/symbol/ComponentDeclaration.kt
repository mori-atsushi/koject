package com.moriatsushi.koject.processor.symbol

import com.moriatsushi.koject.internal.StringIdentifier
import com.moriatsushi.koject.processor.code.Names
import com.moriatsushi.koject.processor.code.escapedForCode
import com.squareup.kotlinpoet.ClassName

internal sealed class ComponentDeclaration(
    private val containerName: String,
    private val factories: Sequence<FactoryDeclaration>,
) {
    abstract val name: ComponentName
    abstract val extrasHolders: Sequence<ExtrasHolderDeclaration>
    abstract val allProvided: List<Provided>

    class Root(
        containerName: String,
        factories: Sequence<FactoryDeclaration>,
        override val extrasHolders: Sequence<ExtrasHolderDeclaration>,
    ) : ComponentDeclaration(containerName, factories) {
        override val name = ComponentName("RootComponent")

        override val allProvided: List<Provided>
            get() = allFactories.map { it.provided } +
                extrasHolders.flatMap { it.extras }
    }

    class Child(
        containerName: String,
        factories: Sequence<FactoryDeclaration>,
        val extrasHolder: ComponentExtrasHolderDeclaration,
        val rootComponent: Root,
    ) : ComponentDeclaration(containerName, factories) {
        override val name = extrasHolder.componentName

        override val allProvided: List<Provided>
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

    val allFactories = factories.groupBy { it.identifier }
        .map { (_, factories) ->
            if (factories.any { it.forTest }) {
                factories.filter { it.forTest }
            } else {
                factories
            }
        }
        .flatten()
    val normalFactories = allFactories.filter { !it.isSingleton }
    val singletonFactories = allFactories.filter { it.isSingleton }

    fun findFactory(identifier: StringIdentifier): FactoryDeclaration? {
        return factories.find { it.identifier == identifier }
    }

    val containerClassName: ClassName
        get() = ClassName(
            "${Names.generatedPackageName}.$containerName",
            "_${name.value.escapedForCode}_Container",
        )
}
