package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.processing.Resolver

internal class AllDeclarations(
    private val factories: Sequence<FactoryDeclaration>,
    private val extrasHolders: Sequence<ExtrasHolderDeclaration>,
    componentExtrasHolders: Sequence<ComponentExtrasHolderDeclaration>,
) {
    private val componentExtrasHolders =
        filterValidComponentExtrasHolders(componentExtrasHolders)

    val mainContainer: ContainerDeclaration
        get() = ContainerDeclaration(
            name = "main",
            factories = factories.filterNot { it.forTest },
            extrasHolders = extrasHolders,
            componentExtrasHolders = componentExtrasHolders,
        )

    val testContainer: ContainerDeclaration
        get() = ContainerDeclaration(
            name = "test",
            factories = factories,
            extrasHolders = extrasHolders,
            componentExtrasHolders = componentExtrasHolders,
        )

    private fun filterValidComponentExtrasHolders(
        componentExtrasHolders: Sequence<ComponentExtrasHolderDeclaration>,
    ): Sequence<ComponentExtrasHolderDeclaration> {
        val list = mutableListOf<ComponentExtrasHolderDeclaration>()
        val map = componentExtrasHolders.groupBy { it.componentName }
        map.forEach { (_, extrasHolders) ->
            if (extrasHolders.all { it.copied }) {
                val min = extrasHolders.minBy { it.copiedCount }
                list.add(min)
            } else {
                val originals = extrasHolders.filterNot { it.copied }
                list.addAll(originals)
            }
        }
        return list.asSequence()
    }
}

internal fun Resolver.collectAllDeclarations(): AllDeclarations {
    return AllDeclarations(
        factories = findFactories(),
        extrasHolders = findExtrasHolders(),
        componentExtrasHolders = findComponentExtrasHolders(),
    )
}
