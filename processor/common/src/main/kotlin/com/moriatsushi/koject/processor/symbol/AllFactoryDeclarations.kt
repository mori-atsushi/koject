package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.moriatsushi.koject.processor.code.Names

internal data class AllFactoryDeclarations(
    val rootComponent: ComponentDeclaration,
    val childComponents: Sequence<ComponentDeclaration>,
)

internal fun Resolver.collectAllFactoryDeclarations(): AllFactoryDeclarations {
    @OptIn(KspExperimental::class)
    val factories = getDeclarationsFromPackage(Names.factoryPackageName)
        .filterIsInstance<KSClassDeclaration>()
        .map { FactoryDeclaration.of(it) }

    val rootComponent = ComponentDeclaration.createRoot(
        factories.filter { it.component == null },
    )

    @OptIn(KspExperimental::class)
    val childComponents = getDeclarationsFromPackage(Names.componentPackageName)
        .filterIsInstance<KSClassDeclaration>()
        .map { ComponentDeclaration.of(it, factories) }

    return AllFactoryDeclarations(
        rootComponent,
        childComponents,
    )
}
