package com.moriatsushi.koject.processor.container

import com.moriatsushi.koject.processor.error.DuplicateComponentExtrasException
import com.moriatsushi.koject.processor.error.DuplicateProvidedException
import com.moriatsushi.koject.processor.error.NotProvidedException
import com.moriatsushi.koject.processor.error.WrongScopeException
import com.moriatsushi.koject.processor.symbol.AllFactoryDeclarations
import com.moriatsushi.koject.processor.symbol.ComponentDeclaration
import com.moriatsushi.koject.processor.symbol.ComponentExtrasHolderDeclaration
import com.moriatsushi.koject.processor.symbol.ComponentName
import com.moriatsushi.koject.processor.symbol.Dependency
import com.moriatsushi.koject.processor.symbol.FactoryDeclaration
import com.moriatsushi.koject.processor.symbol.displayName

internal class DependencyValidator {
    fun validate(
        allFactories: AllFactoryDeclarations,
    ) {
        validateRootComponent(allFactories.rootComponent)
        validateComponentExtras(allFactories.extrasHolders)
        allFactories.childComponents.forEach {
            validateChildComponent(it, allFactories.rootComponent)
        }
    }

    private fun validateComponentExtras(
        extrasHolders: Sequence<ComponentExtrasHolderDeclaration>,
    ) {
        extrasHolders.forEach { extrasHolder ->
            val duplicate = extrasHolders.filter {
                it.componentName == extrasHolder.componentName
            }
            if (duplicate.count() > 1) {
                val errorMessage = buildString {
                    append(extrasHolder.componentName.value)
                    append(" has a duplicate ComponentExtras definition.\n")
                    duplicate.forEachIndexed { index, it ->
                        appendLine("    ${index + 1}. ${it.location.value}")
                    }
                }
                throw DuplicateComponentExtrasException(errorMessage)
            }
        }
    }

    private fun validateRootComponent(
        component: ComponentDeclaration.Root,
    ) {
        val dependencies = component.allFactories.map { it.asDependency() }
        validateDependencies(component.allFactories, dependencies)

        component.allFactories.forEach { factory ->
            factory.parameters.forEach {
                val dependencyFactory = component.findFactory(it.identifier)!!
                if (factory.isSingleton && !dependencyFactory.isSingleton) {
                    throwWrongScopeException(it)
                }
            }
        }
    }

    private fun validateChildComponent(
        component: ComponentDeclaration.Child,
        rootComponent: ComponentDeclaration.Root,
    ) {
        val dependencies = component.extrasHolder.extras +
            component.allFactories.map { it.asDependency() } +
            rootComponent.allFactories.map { it.asDependency() }

        validateDependencies(component.allFactories, dependencies)
    }

    private fun validateDependencies(
        factories: Sequence<FactoryDeclaration>,
        dependencies: Sequence<Dependency>,
    ) {
        factories.forEach { factory ->
            validateDuplicates(factory, dependencies)
            factory.parameters.forEach {
                validateParameter(factory, it, dependencies)
            }
        }
    }

    private fun validateDuplicates(
        factory: FactoryDeclaration,
        dependencies: Sequence<Dependency>,
    ) {
        val duplicate = dependencies.filter {
            it.identifier == factory.identifier
        }
        if (duplicate.count() > 1) {
            val errorMessage = buildString {
                append("${factory.identifier.displayName} provide is duplicated")
                if (factory.component != null) {
                    append(" in Component(${factory.component.value})")
                }
                append(".\n")
                duplicate.forEachIndexed { index, it ->
                    appendLine("    ${index + 1}. ${it.location.value}")
                }
            }
            throw DuplicateProvidedException(errorMessage)
        }
    }

    private fun validateParameter(
        factory: FactoryDeclaration,
        parameter: Dependency,
        dependencies: Sequence<Dependency>,
    ) {
        val dependencyFactory = dependencies.find {
            it.identifier == parameter.identifier
        }
        if (dependencyFactory == null) {
            throwNotProvidedException(parameter, factory.component)
        }
    }

    private fun throwNotProvidedException(
        parameter: Dependency,
        component: ComponentName?,
    ) {
        val message = buildString {
            append(parameter.location.value)
            append(": ")
            append(parameter.identifier.displayName)
            append(" is not provided")
            if (component != null) {
                append(" in Component(")
                append(component.value)
                append(")")
            }
            append(".")
        }
        throw NotProvidedException(message)
    }

    private fun throwWrongScopeException(
        parameter: Dependency,
    ) {
        throw WrongScopeException(
            "${parameter.location.value}: " +
                "${parameter.identifier.displayName} cannot be injected " +
                "because it is not a singleton. " +
                "Only a singleton can be injected into singletons.",
        )
    }
}
