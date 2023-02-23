package com.moriatsushi.koject.processor.code

import com.moriatsushi.koject.internal.identifier.Identifier
import com.moriatsushi.koject.processor.identifier.escapedValue
import com.squareup.kotlinpoet.ClassName

internal object Names {
    const val rootPackageName = "com.moriatsushi.koject"
    const val generatedPackageName = "$rootPackageName.generated"
    const val factoryPackageName = "$generatedPackageName.factory"

    val containerClassName = ClassName(generatedPackageName, "_AppContainer")

    fun providerNameOf(identifier: Identifier): String {
        return "provide_${identifier.escapedValue}"
    }

    fun factoryNameOf(identifier: Identifier): String {
        return "_${identifier.escapedValue}_Factory"
    }
}
