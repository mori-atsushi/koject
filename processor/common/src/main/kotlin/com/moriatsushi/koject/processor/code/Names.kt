package com.moriatsushi.koject.processor.code

import com.moriatsushi.koject.internal.StringIdentifier
import com.moriatsushi.koject.processor.symbol.ProviderDeclaration
import com.moriatsushi.koject.processor.symbol.ProviderName
import com.moriatsushi.koject.processor.symbol.asCodeName

internal object Names {
    const val rootPackageName = "com.moriatsushi.koject"
    const val testPackageName = "$rootPackageName.test"
    const val generatedPackageName = "$rootPackageName.generated"
    const val factoryPackageName = "$generatedPackageName.factory"
    const val extrasPackageName = "$generatedPackageName.extras"
    const val componentPackageName = "$generatedPackageName.component"

    fun providerNameOf(identifier: StringIdentifier): String {
        return "provide_${identifier.asCodeName()}"
    }

    fun factoryNameOf(provider: ProviderDeclaration): String {
        val identifier = provider.identifier.asStringIdentifier()
        val functionName = when (provider.name) {
            is ProviderName.Class -> {
                null
            }
            is ProviderName.Function -> {
                provider.name.functionName
            }
        }
        return buildString {
            append("_")
            append(identifier.asCodeName())
            if (functionName != null) {
                append("__")
                append(functionName.canonicalName.hashForCode)
            }
            append("_Factory")
        }
    }
}
