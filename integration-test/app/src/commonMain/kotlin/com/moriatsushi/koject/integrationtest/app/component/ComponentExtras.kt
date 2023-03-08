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
    val extra: ExtraClass,
)

class ExtraClass

@HasExtrasComponent
@Provides
class HasExtrasComponentClass(
    val extra: ExtraClass,
)
