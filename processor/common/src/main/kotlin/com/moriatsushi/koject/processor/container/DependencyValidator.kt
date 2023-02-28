package com.moriatsushi.koject.processor.container

import com.moriatsushi.koject.internal.identifier.StringIdentifier
import com.moriatsushi.koject.processor.error.NotProvidedException
import com.moriatsushi.koject.processor.error.WrongScopeException
import com.moriatsushi.koject.processor.symbol.AllFactoryDeclarations
import com.moriatsushi.koject.processor.symbol.FactoryDeclaration
import com.moriatsushi.koject.processor.symbol.displayName

internal class DependencyValidator {
    fun validate(
        allFactories: AllFactoryDeclarations,
    ) {
        allFactories.all.forEach { targetClass ->
            targetClass.dependencies.forEach {
                validate(allFactories, targetClass, it)
            }
        }
    }

    private fun validate(
        allFactories: AllFactoryDeclarations,
        target: FactoryDeclaration,
        dependency: StringIdentifier,
    ) {
        val dependencyFactory = allFactories.getOrNull(dependency)
        when {
            dependencyFactory == null -> {
                throwNotProvidedException(target.identifier, dependency)
            }
            target.isSingleton && !dependencyFactory.isSingleton -> {
                throwWrongScopeException(target.identifier, dependency)
            }
        }
    }

    private fun throwNotProvidedException(
        target: StringIdentifier,
        dependency: StringIdentifier,
    ) {
        throw NotProvidedException(
            "${dependency.displayName} is not provided. " +
                "It is requested by ${target.displayName}.",
        )
    }

    private fun throwWrongScopeException(
        target: StringIdentifier,
        dependency: StringIdentifier,
    ) {
        throw WrongScopeException(
            "${target.displayName} cannot be created " +
                "because ${dependency.displayName} is not a singleton. " +
                "Only a singleton can be injected into singletons.",
        )
    }
}
