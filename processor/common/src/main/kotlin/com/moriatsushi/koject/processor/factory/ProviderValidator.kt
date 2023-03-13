package com.moriatsushi.koject.processor.factory

import com.moriatsushi.koject.internal.Location
import com.moriatsushi.koject.processor.error.CodeGenerationException
import com.moriatsushi.koject.processor.symbol.ProviderDeclaration

internal class ProviderValidator {
    fun validate(provider: ProviderDeclaration) {
        if (provider.identifier.typeName.isNullable) {
            throw CodeGenerationException(
                "${provider.location.value}: Cannot provide a nullable type",
            )
        }
        provider.parameters?.forEach {
            if (it.identifier.typeName.isNullable) {
                throw CodeGenerationException(
                    "${it.location.value}: Cannot inject a nullable type",
                )
            }
        }
        if (provider.isSingleton && provider.component != null) {
            throwComponentWithSingletonException(provider.location)
        }
    }

    private fun throwComponentWithSingletonException(location: Location): Nothing {
        throw CodeGenerationException(
            "${location.value}: Component type cannot be Singleton",
        )
    }
}
