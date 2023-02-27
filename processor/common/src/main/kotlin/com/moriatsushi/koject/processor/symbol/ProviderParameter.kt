package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSValueParameter

internal class ProviderParameter(
    private val ksValueParameter: KSValueParameter,
) {
    val identifier by lazy {
        ksValueParameter.findStringIdentifier()!!
    }
}
