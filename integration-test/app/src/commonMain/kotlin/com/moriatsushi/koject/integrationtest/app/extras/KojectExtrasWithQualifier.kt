@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.app.extras

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Qualifier
import com.moriatsushi.koject.extras.KojectExtras

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ConstructorQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PropertyQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GetterQualifier

@KojectExtras
class WithQualifierExtras(
    @ConstructorQualifier
    val constructorValue: String = "constructor",
) {
    @PropertyQualifier
    val propertyValue: String = "property"

    @GetterQualifier
    val getterValue: String
        get() = "getter"
}

@Provides
class WithQualifierExtrasHolder(
    @ConstructorQualifier
    val constructorValue: String,
    @PropertyQualifier
    val propertyValue: String,
    @GetterQualifier
    val getterValue: String,
)
