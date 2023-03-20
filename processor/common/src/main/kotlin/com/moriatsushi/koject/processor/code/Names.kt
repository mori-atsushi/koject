package com.moriatsushi.koject.processor.code

import com.moriatsushi.koject.internal.StringIdentifier
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
}
