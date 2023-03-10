@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.app.extras

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.component.Component
import com.moriatsushi.koject.component.ComponentExtras

@Component
annotation class KojectExtrasComponent

class ComponentExtra

@ComponentExtras(KojectExtrasComponent::class)
class KojectExtrasComponentExtras(
    val extra: ComponentExtra,
)

@KojectExtrasComponent
@Provides
class KojectExtrasComponentClass(
    val globalExtraClass1: GlobalExtraClass1,
    val globalExtraSingleton: GlobalExtraSingleton1,
    val componentExtra: ComponentExtra,
)
