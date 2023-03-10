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
    @Named("component-constructor")
    val constructorValue: String = "component-named-constructor",
) {
    @Named("component-property")
    val propertyValue: String = "component-named-property"

    @Named("component-getter")
    val getterValue: String
        get() = "component-named-getter"
}

@WithNamedComponent
@Provides
class WithNamedComponentClass(
    @Named("component-constructor")
    val constructorValue: String,
    @Named("component-property")
    val propertyValue: String,
    @Named("component-getter")
    val getterValue: String,
)
