package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSValueParameter
import com.moriatsushi.koject.processor.analytics.findIdentifier

internal class ProviderParameter(
    private val ksValueParameter: KSValueParameter,
) {
    val identifier by lazy {
        ksValueParameter.findIdentifier()!!
    }
}
