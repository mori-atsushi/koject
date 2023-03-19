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
    abstract val allProvided: Sequence<Provided>

    class Root(
        containerName: String,
        factories: Sequence<FactoryDeclaration>,
        override val extrasHolders: Sequence<ExtrasHolderDeclaration>,
    ) : ComponentDeclaration(containerName, factories) {
        override val name = ComponentName("RootComponent")

        override val allProvided: Sequence<Provided>
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

    val allFactories = filterValidFactories(factories)
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

    private fun filterValidFactories(
        factories: Sequence<FactoryDeclaration>,
    ): Sequence<FactoryDeclaration> {
        val list = mutableListOf<FactoryDeclaration>()
        val map = factories.groupBy { it.identifier to it.component }
        map.forEach { (_, factories) ->
            val target = if (factories.any { it.forTest }) {
                factories.filter { it.forTest }
            } else {
                factories
            }
            if (target.all { it.copied }) {
                val min = target.minBy { it.copiedCount }
                list.add(min)
            } else {
                val originals = target.filterNot { it.copied }
                list.addAll(originals)
            }
        }
        return list.asSequence()
    }
}
