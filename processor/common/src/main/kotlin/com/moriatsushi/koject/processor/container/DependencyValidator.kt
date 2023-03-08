package com.moriatsushi.koject.processor.container

import com.moriatsushi.koject.processor.error.DuplicateProvidedException
import com.moriatsushi.koject.processor.error.NotProvidedException
import com.moriatsushi.koject.processor.error.WrongScopeException
import com.moriatsushi.koject.processor.symbol.AllFactoryDeclarations
import com.moriatsushi.koject.processor.symbol.ComponentDeclaration
import com.moriatsushi.koject.processor.symbol.ComponentName
import com.moriatsushi.koject.processor.symbol.Dependency
import com.moriatsushi.koject.processor.symbol.FactoryDeclaration
import com.moriatsushi.koject.processor.symbol.displayName

internal class DependencyValidator {
    fun validate(
        allFactories: AllFactoryDeclarations,
    ) {
        validateComponent(allFactories.rootComponent, null)
        allFactories.childComponents.forEach {
            validateComponent(it, allFactories.rootComponent)
        }
        validateScope(allFactories.rootComponent)
    }

    private fun validateScope(
        rootComponent: ComponentDeclaration,
    ) {
        rootComponent.allFactories.forEach { factory ->
            factory.parameters.forEach {
                val dependencyFactory = rootComponent.findFactory(it.identifier)!!
                if (factory.isSingleton && !dependencyFactory.isSingleton) {
                    throwWrongScopeException(it)
                }
            }
        }
    }

    private fun validateComponent(
        component: ComponentDeclaration,
        rootComponent: ComponentDeclaration?,
    ) {
        val dependencies = component.extras +
            component.allFactories.map { it.asDependency() } +
            rootComponent?.allFactories.orEmpty().map { it.asDependency() }

        component.allFactories.forEach { factory ->
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
