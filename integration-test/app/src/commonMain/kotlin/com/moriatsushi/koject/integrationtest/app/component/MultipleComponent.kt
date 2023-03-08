@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.app.component

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.component.Component
import com.moriatsushi.koject.component.ComponentExtras

@Component
@Retention(AnnotationRetention.BINARY)
annotation class CustomComponent1

@ComponentExtras(CustomComponent1::class)
class CustomComponent1Extras

@Component
@Retention(AnnotationRetention.BINARY)
annotation class CustomComponent2

@ComponentExtras(CustomComponent2::class)
class CustomComponent2Extras

class MultipleCustomComponentClass(
    val value: String,
)

@Provides
@CustomComponent1
fun provideCustomComponent1(): MultipleCustomComponentClass {
    return MultipleCustomComponentClass("custom-component-1")
}

@Provides
@CustomComponent2
fun provideCustomComponent2(): MultipleCustomComponentClass {
    return MultipleCustomComponentClass("custom-component-2")
}

@Provides
@CustomComponent1
class CustomComponent1Holder(
    val value: MultipleCustomComponentClass,
)

@Provides
@CustomComponent2
class CustomComponent2Holder(
    val value: MultipleCustomComponentClass,
)
