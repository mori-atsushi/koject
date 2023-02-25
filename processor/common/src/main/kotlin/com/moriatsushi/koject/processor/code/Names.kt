package com.moriatsushi.koject.processor.code

import com.squareup.kotlinpoet.ClassName

internal object Names {
    const val rootPackageName = "com.moriatsushi.koject"
    const val generatedPackageName = "$rootPackageName.generated"
    const val factoryPackageName = "$generatedPackageName.factory"

    val containerClassName = ClassName(generatedPackageName, "_AppContainer")

    fun providerNameOf(identifier: String): String {
        return "provide_${identifier.asCodeName()}"
    }

    fun instanceNameOf(identifier: String): String {
        return identifier.asCodeName()
    }

    fun factoryNameOf(identifier: String): String {
        return "_${identifier.asCodeName()}_Factory"
    }

    /**
     * Name that can be used in code for functions, classes, etc.
     */
    private fun String.asCodeName(): String {
        return replace(" ", "")
            .replace("-", "___")
            .replace(".", "_")
            .replace("<", "__")
            .replace(">", "__")
            .replace(",", "__")
            .replace("?", "_nullable")
    }
}
