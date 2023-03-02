package com.moriatsushi.koject.processor.container

import com.moriatsushi.koject.processor.error.DuplicateProvidedException
import com.moriatsushi.koject.processor.error.NotProvidedException
import com.moriatsushi.koject.processor.error.WrongScopeException
import com.moriatsushi.koject.processor.symbol.AllFactoryDeclarations
import com.moriatsushi.koject.processor.symbol.FactoryDeclaration
import com.moriatsushi.koject.processor.symbol.FactoryParameter
import com.moriatsushi.koject.processor.symbol.displayName

internal class DependencyValidator {
    fun validate(
        allFactories: AllFactoryDeclarations,
    ) {
        allFactories.all.forEach { factory ->
            validateDuplicates(factory, allFactories)
            factory.parameters.forEach {
                validateParameter(it, factory, allFactories)
            }
        }
    }

    private fun validateParameter(
        parameter: FactoryParameter,
        factory: FactoryDeclaration,
        allFactories: AllFactoryDeclarations,
    ) {
        val dependencyFactory = allFactories.getOrNull(parameter.identifier)
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
        allFactories: AllFactoryDeclarations,
    ) {
        val duplicate = allFactories.all.filter {
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
        parameter: FactoryParameter,
    ) {
        throw NotProvidedException(
            "${parameter.location.value}: " +
                "${parameter.identifier.displayName} is not provided.",
        )
    }

    private fun throwWrongScopeException(
        parameter: FactoryParameter,
    ) {
        throw WrongScopeException(
            "${parameter.location.value}: " +
                "${parameter.identifier.displayName} cannot be injected " +
                "because it is not a singleton. " +
                "Only a singleton can be injected into singletons.",
        )
    }
}
