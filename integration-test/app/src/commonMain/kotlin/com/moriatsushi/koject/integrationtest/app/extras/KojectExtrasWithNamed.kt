@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.app.extras

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Named
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.extras.KojectExtras

@KojectExtras
class WithNamedExtras(
    @Named("constructor")
    val constructorValue: String = "named-constructor",
) {
    @Named("property")
    val propertyValue: String = "named-property"

    @Named("getter")
    val getterValue: String
        get() = "named-getter"
}

@Provides
class WithNamedComponentExtrasHolder(
    @Named("constructor")
    val constructorValue: String,
    @Named("property")
    val propertyValue: String,
    @Named("getter")
    val getterValue: String,
)
