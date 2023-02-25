package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSValueParameter
import com.moriatsushi.koject.processor.analytics.findIdentifier
import com.moriatsushi.koject.processor.identifier.escapedValue

internal class ProviderParameter(
    private val ksValueParameter: KSValueParameter,
) {
    val identifier by lazy {
        ksValueParameter.findIdentifier()!!
    }

    val providerName: String
        get() = "provide_${identifier.escapedValue}"
}
