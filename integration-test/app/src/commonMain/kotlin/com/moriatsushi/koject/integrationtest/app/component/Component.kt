@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.app.component

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.component.Component
import com.moriatsushi.koject.component.ComponentExtras
import com.moriatsushi.koject.integrationtest.app.AppClass1
import com.moriatsushi.koject.integrationtest.app.SingletonClass

@Component
@Retention(AnnotationRetention.BINARY)
annotation class CustomComponent

class CustomComponentExtras : ComponentExtras<CustomComponent>

@CustomComponent
@Provides
class CustomComponentClass(
    val appClass1: AppClass1,
    val singletonClass: SingletonClass,
)
