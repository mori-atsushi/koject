@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.app.component

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Qualifier
import com.moriatsushi.koject.component.Component
import com.moriatsushi.koject.component.ComponentExtras

@Component
@Retention(AnnotationRetention.BINARY)
annotation class WithQualifierComponent

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ConstructorQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PropertyQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GetterQualifier

@ComponentExtras(WithQualifierComponent::class)
class WithQualifierComponentExtras(
    @ConstructorQualifier
    val constructorValue: String = "constructor",
) {
    @PropertyQualifier
    val propertyValue: String = "property"

    @GetterQualifier
    val getterValue: String
        get() = "getter"
}

@WithQualifierComponent
@Provides
class WithQualifierComponentClass(
    @ConstructorQualifier
    val constructorValue: String,
    @PropertyQualifier
    val propertyValue: String,
    @GetterQualifier
    val getterValue: String,
)
