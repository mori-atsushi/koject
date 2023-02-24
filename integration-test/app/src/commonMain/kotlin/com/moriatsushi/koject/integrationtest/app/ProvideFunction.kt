package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Provides

@Provides
fun provideString(): String {
    return "provided"
}

@Provides
fun provideInterface(): ProvidableInterface {
    return object : ProvidableInterface {}
}

@Provides
fun provideWithParameters(
    string: String,
    type: ProvidableInterface,
): ProvideFunctionWithParameters {
    return ProvideFunctionWithParameters(string, type)
}

interface ProvidableInterface

data class ProvideFunctionWithParameters(
    val string: String,
    val type: ProvidableInterface,
)
