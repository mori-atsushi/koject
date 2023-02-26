package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSValueParameter
import com.moriatsushi.koject.internal.identifier.StringIdentifier

internal class ProviderParameter(
    private val ksValueParameter: KSValueParameter,
) {
    val identifier by lazy {
        StringIdentifier.ofOrNull(ksValueParameter)!!
    }
}
