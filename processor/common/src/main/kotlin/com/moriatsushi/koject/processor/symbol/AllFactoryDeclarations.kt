package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.moriatsushi.koject.processor.code.Names

internal class AllFactoryDeclarations(
    factories: Sequence<FactoryDeclaration>,
    components: Sequence<ComponentClassDeclaration>,
) {
    val rootComponent = ComponentFactoryDeclarations(
        factories.filter { it.component == null },
    )

    val components: Map<ComponentClassDeclaration, ComponentFactoryDeclarations> =
        components
            .sortedBy { it.className.canonicalName }
            .associateWith { component ->
                ComponentFactoryDeclarations(
                    factories.filter { it.component == component.name },
                )
            }
}

internal fun Resolver.collectAllFactoryDeclarations(): AllFactoryDeclarations {
    @OptIn(KspExperimental::class)
    val factories = getDeclarationsFromPackage(Names.factoryPackageName)
        .filterIsInstance<KSClassDeclaration>()
        .map { FactoryDeclaration.of(it) }

    @OptIn(KspExperimental::class)
    val components = getDeclarationsFromPackage(Names.componentPackageName)
        .filterIsInstance<KSClassDeclaration>()
        .map { ComponentClassDeclaration.of(it) }
    return AllFactoryDeclarations(factories, components)
}
