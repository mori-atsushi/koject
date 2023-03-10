package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.moriatsushi.koject.processor.code.Names

internal class AllFactoryDeclarations(
    val factories: Sequence<FactoryDeclaration>,
    extrasHolders: Sequence<ExtrasHolderDeclaration>,
    val componentExtrasHolders: Sequence<ComponentExtrasHolderDeclaration>,
) {
    val rootComponent: ComponentDeclaration.Root =
        ComponentDeclaration.Root(
            factories = factories.filter { it.component == null },
            extrasHolders = extrasHolders,
        )

    val childComponents: Sequence<ComponentDeclaration.Child> =
        componentExtrasHolders.map { extrasHolder ->
            ComponentDeclaration.Child(
                extrasHolder = extrasHolder,
                factories = factories.filter {
                    it.component == extrasHolder.componentName
                },
                rootComponent = rootComponent,
            )
        }
}

internal fun Resolver.collectAllFactoryDeclarations(): AllFactoryDeclarations {
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

    return AllFactoryDeclarations(
        factories = factories,
        extrasHolders = extrasHolders,
        componentExtrasHolders = componentExtrasHolders,
    )
}
