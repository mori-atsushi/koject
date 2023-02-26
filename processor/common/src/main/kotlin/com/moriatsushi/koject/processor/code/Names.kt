package com.moriatsushi.koject.processor.code

import com.moriatsushi.koject.internal.identifier.StringIdentifier
import com.moriatsushi.koject.processor.symbol.asCodeName
import com.squareup.kotlinpoet.ClassName

internal object Names {
    const val rootPackageName = "com.moriatsushi.koject"
    const val generatedPackageName = "$rootPackageName.generated"
    const val factoryPackageName = "$generatedPackageName.factory"

    val containerClassName = ClassName(generatedPackageName, "_AppContainer")

    fun providerNameOf(identifier: StringIdentifier): String {
        return "provide_${identifier.asCodeName()}"
    }

    fun instanceNameOf(identifier: StringIdentifier): String {
        return identifier.asCodeName()
    }

    fun factoryNameOf(identifier: StringIdentifier): String {
        return "_${identifier.asCodeName()}_Factory"
    }
}
