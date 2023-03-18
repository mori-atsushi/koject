package com.moriatsushi.koject.processor.container

import com.moriatsushi.koject.processor.error.DuplicateComponentExtrasException
import com.moriatsushi.koject.processor.error.DuplicateProvidedException
import com.moriatsushi.koject.processor.error.NotProvidedException
import com.moriatsushi.koject.processor.error.WrongScopeException
import com.moriatsushi.koject.processor.symbol.ComponentDeclaration
import com.moriatsushi.koject.processor.symbol.ComponentExtrasHolderDeclaration
import com.moriatsushi.koject.processor.symbol.ComponentName
import com.moriatsushi.koject.processor.symbol.ContainerDeclaration
import com.moriatsushi.koject.processor.symbol.Dependency
import com.moriatsushi.koject.processor.symbol.FactoryDeclaration
import com.moriatsushi.koject.processor.symbol.Provided
import com.moriatsushi.koject.processor.symbol.displayName

internal class ContainerValidator {
    fun validate(
        container: ContainerDeclaration,
    ) {
        validateRootComponent(container.rootComponent)
        validateComponentExtras(container.componentExtrasHolders)
        container.childComponents.forEach {
            validateDependencies(it)
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
        val provided = component.allProvided
        validateDependencies(component)

        component.allFactories.forEach { factory ->
            factory.parameters.forEach { dependency ->
                val resolved = provided.find {
                    it.identifier == dependency.identifier
                }!!
                if (factory.isSingleton && !resolved.isSingleton) {
                    throwWrongScopeException(dependency)
                }
            }
        }
    }

    private fun validateDependencies(
        component: ComponentDeclaration,
    ) {
        val allProvided = component.allProvided
        val componentName = (component as? ComponentDeclaration.Child)?.name

        component.allFactories.forEach { factory ->
            factory.parameters.forEach {
                validateParameter(factory, it, allProvided)
            }
            validateDuplicates(
                target = factory.provided,
                allProvided = allProvided,
                component = componentName,
            )
        }

        component.extrasHolders.forEach { extrasHolder ->
            extrasHolder.extras.forEach {
                validateDuplicates(
                    target = it,
                    allProvided = allProvided,
                    component = componentName,
                )
            }
        }
    }

    private fun validateDuplicates(
        target: Provided,
        allProvided: List<Provided>,
        component: ComponentName?,
    ) {
        val duplicate = allProvided.filter { it.identifier == target.identifier }
        if (duplicate.count() > 1) {
            val errorMessage = buildString {
                append("${target.identifier.displayName} provide is duplicated")
                if (component != null) {
                    append(" in Component(${component.value})")
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
        allProvided: List<Provided>,
    ) {
        val dependencyFactory = allProvided.find {
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
