package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.processing.Resolver

internal class AllDeclarations(
    private val factories: Sequence<FactoryDeclaration>,
    private val extrasHolders: Sequence<ExtrasHolderDeclaration>,
    private val componentExtrasHolders: Sequence<ComponentExtrasHolderDeclaration>,
) {
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
}

internal fun Resolver.collectAllDeclarations(): AllDeclarations {
    return AllDeclarations(
        factories = findFactories(),
        extrasHolders = findExtrasHolders(),
        componentExtrasHolders = findComponentExtrasHolders(),
    )
}
