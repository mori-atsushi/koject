package com.moriatsushi.koject.processor.container

import com.moriatsushi.koject.internal.identifier.StringIdentifier
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
        target: FactoryDeclaration,
        dependency: FactoryParameter,
    ) {
        val dependencyFactory = allFactories.getOrNull(dependency.identifier)
        when {
            dependencyFactory == null -> {
                throwNotProvidedException(target.identifier, dependency.identifier)
            }
            target.isSingleton && !dependencyFactory.isSingleton -> {
                throwWrongScopeException(target.identifier, dependency.identifier)
            }
        }
    }

    private fun throwNotProvidedException(
        target: StringIdentifier,
        dependency: StringIdentifier,
    ) {
        throw NotProvidedException(
            """
            |"${dependency.displayName} is not provided.
            |It is requested by ${target.displayName}.",
            |
            """.trimMargin(),
        )
    }

    private fun throwWrongScopeException(
        target: StringIdentifier,
        dependency: StringIdentifier,
    ) {
        throw WrongScopeException(
            """
            |${target.displayName} cannot be created because ${dependency.displayName} is not a singleton.
            |Only a singleton can be requested from a singleton.
            |
            """.trimMargin(),
        )
    }
}
