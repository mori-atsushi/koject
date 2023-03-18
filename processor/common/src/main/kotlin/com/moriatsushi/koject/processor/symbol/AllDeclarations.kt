package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.moriatsushi.koject.processor.code.Names

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
    @OptIn(KspExperimental::class)
    val factories = getDeclarationsFromPackage(Names.factoryPackageName)
        .filterIsInstance<KSClassDeclaration>()
        .map { FactoryDeclaration.of(it) }

    @OptIn(KspExperimental::class)
    val extrasHolders = getDeclarationsFromPackage(Names.extrasPackageName)
        .filterIsInstance<KSClassDeclaration>()
        .map { ExtrasHolderDeclaration.of(it) }

    @OptIn(KspExperimental::class)
    val componentExtrasHolders = getDeclarationsFromPackage(Names.componentPackageName)
        .filterIsInstance<KSClassDeclaration>()
        .map { ComponentExtrasHolderDeclaration.of(it) }

    return AllDeclarations(
        factories = factories,
        extrasHolders = extrasHolders,
        componentExtrasHolders = componentExtrasHolders,
    )
}
