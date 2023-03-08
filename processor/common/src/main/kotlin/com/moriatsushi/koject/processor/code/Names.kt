package com.moriatsushi.koject.processor.code

import com.moriatsushi.koject.internal.StringIdentifier
import com.moriatsushi.koject.processor.symbol.ProviderDeclaration
import com.moriatsushi.koject.processor.symbol.asCodeName
import com.squareup.kotlinpoet.ClassName

internal object Names {
    const val rootPackageName = "com.moriatsushi.koject"
    const val generatedPackageName = "$rootPackageName.generated"
    const val factoryPackageName = "$generatedPackageName.factory"
    const val componentPackageName = "$generatedPackageName.component"

    val appContainerClassName = ClassName(generatedPackageName, "_AppContainer")

    fun providerNameOf(identifier: StringIdentifier): String {
        return "provide_${identifier.asCodeName()}"
    }

    fun factoryNameOf(provider: ProviderDeclaration): String {
        val identifier = provider.identifier.asStringIdentifier()
        val functionName = when (provider) {
            is ProviderDeclaration.Class -> {
                null
            }
            is ProviderDeclaration.ObjectFunction -> {
                provider.functionName
            }
            is ProviderDeclaration.TopLevelFunction -> {
                provider.functionName
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
