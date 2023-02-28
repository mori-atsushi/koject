package com.moriatsushi.koject.processor.factory

import com.moriatsushi.koject.processor.error.DuplicateProvidedException
import com.moriatsushi.koject.processor.symbol.ProviderDeclaration

internal class ProviderValidator {
    fun validate(providers: Sequence<ProviderDeclaration>) {
        providers.forEach { provider ->
            val duplicate = providers.filter {
                it.identifier == provider.identifier
            }
            if (duplicate.count() > 1) {
                val errorMessage = buildString {
                    appendLine("${provider.identifier.displayName} provide is duplicated.")
                    duplicate.forEachIndexed { index, it ->
                        appendLine("    ${index + 1}. ${it.location.value}")
                    }
                }
                throw DuplicateProvidedException(errorMessage)
            }
        }
    }
}
