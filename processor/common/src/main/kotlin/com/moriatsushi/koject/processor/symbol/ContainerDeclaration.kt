package com.moriatsushi.koject.processor.symbol

import com.moriatsushi.koject.processor.code.Names
import com.squareup.kotlinpoet.ClassName

internal class ContainerDeclaration(
    private val name: String,
    val factories: Sequence<FactoryDeclaration>,
    extrasHolders: Sequence<ExtrasHolderDeclaration>,
    val componentExtrasHolders: Sequence<ComponentExtrasHolderDeclaration>,
) {
    val rootComponent: ComponentDeclaration.Root =
        ComponentDeclaration.Root(
            containerName = name,
            factories = factories.filter { it.component == null },
            extrasHolders = extrasHolders,
        )

    val childComponents: Sequence<ComponentDeclaration.Child> =
        componentExtrasHolders.map { extrasHolder ->
            ComponentDeclaration.Child(
                containerName = name,
                extrasHolder = extrasHolder,
                factories = factories.filter {
                    it.component == extrasHolder.componentName
                },
                rootComponent = rootComponent,
            )
        }

    val containerClassName: ClassName
        get() {
            val name = name.replaceFirstChar { it.uppercase() }
            return ClassName(
                Names.generatedPackageName,
                "_${name}Container",
            )
        }
}
