@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.app.component

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Named
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.component.Component
import com.moriatsushi.koject.component.ComponentExtras

@Component
@Retention(AnnotationRetention.BINARY)
annotation class WithNamedComponent

@ComponentExtras(WithNamedComponent::class)
class WithNamedComponentExtras(
    @Named("constructor")
    val constructorValue: String = "named-constructor",
) {
    @Named("property")
    val propertyValue: String = "named-property"

    @Named("getter")
    val getterValue: String
        get() = "named-getter"
}

@WithNamedComponent
@Provides
class WithNamedComponentClass(
    @Named("constructor")
    val constructorValue: String,
    @Named("property")
    val propertyValue: String,
    @Named("getter")
    val getterValue: String,
)
