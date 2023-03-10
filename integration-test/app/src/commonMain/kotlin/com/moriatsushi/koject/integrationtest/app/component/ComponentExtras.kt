@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.app.component

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.component.Component
import com.moriatsushi.koject.component.ComponentExtras

@Component
@Retention(AnnotationRetention.BINARY)
annotation class HasExtrasComponent

@ComponentExtras(HasExtrasComponent::class)
class HasExtrasComponentExtras(
    val constructorExtras: ExtraClass1,
) {
    val propertyExtra: ExtraClass2 = ExtraClass2()
    val getterExtras: ExtraClass3
        get() = ExtraClass3()
}

class ExtraClass1
class ExtraClass2
class ExtraClass3

@HasExtrasComponent
@Provides
class HasExtrasComponentClass(
    val constructorExtras: ExtraClass1,
    val propertyExtra: ExtraClass2,
    val getterExtras: ExtraClass3,
)
