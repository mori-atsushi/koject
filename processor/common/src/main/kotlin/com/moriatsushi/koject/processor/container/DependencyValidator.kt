package com.moriatsushi.koject.processor.container

import com.moriatsushi.koject.processor.error.DuplicateProvidedException
import com.moriatsushi.koject.processor.error.NotProvidedException
import com.moriatsushi.koject.processor.error.WrongScopeException
import com.moriatsushi.koject.processor.symbol.AllFactoryDeclarations
import com.moriatsushi.koject.processor.symbol.Dependency
import com.moriatsushi.koject.processor.symbol.FactoryDeclaration
import com.moriatsushi.koject.processor.symbol.displayName

internal class DependencyValidator {
    fun validate(
        allFactories: AllFactoryDeclarations,
    ) {
        val rootComponentFactories = allFactories.rootComponent.allFactories
        rootComponentFactories.forEach { factory ->
            validateDuplicates(factory, rootComponentFactories)
            factory.parameters.forEach {
                validateParameter(factory, it, rootComponentFactories)
            }
        }
        // TODO: validate components
    }

    private fun validateParameter(
        factory: FactoryDeclaration,
        parameter: Dependency,
        enables: Sequence<FactoryDeclaration>,
    ) {
        val dependencyFactory = enables.find {
            it.identifier == parameter.identifier
        }
        when {
            dependencyFactory == null -> {
                throwNotProvidedException(parameter)
            }
            factory.isSingleton && !dependencyFactory.isSingleton -> {
                throwWrongScopeException(parameter)
            }
        }
    }

    private fun validateDuplicates(
        factory: FactoryDeclaration,
        enables: Sequence<FactoryDeclaration>,
    ) {
        val duplicate = enables.filter {
            it.identifier == factory.identifier
        }
        if (duplicate.count() > 1) {
            val errorMessage = buildString {
                appendLine("${factory.identifier.displayName} provide is duplicated.")
                duplicate.forEachIndexed { index, it ->
                    appendLine("    ${index + 1}. ${it.location.value}")
                }
            }
            throw DuplicateProvidedException(errorMessage)
        }
    }

    private fun throwNotProvidedException(
        parameter: Dependency,
    ) {
        throw NotProvidedException(
            "${parameter.location.value}: " +
                "${parameter.identifier.displayName} is not provided.",
        )
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
