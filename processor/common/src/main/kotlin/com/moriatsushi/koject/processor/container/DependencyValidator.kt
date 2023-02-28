package com.moriatsushi.koject.processor.container

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
        allFactories.all.forEach { targetClass ->
            targetClass.parameters.forEach {
                validate(allFactories, targetClass, it)
            }
        }
    }

    private fun validate(
        allFactories: AllFactoryDeclarations,
        factory: FactoryDeclaration,
        parameter: FactoryParameter,
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
