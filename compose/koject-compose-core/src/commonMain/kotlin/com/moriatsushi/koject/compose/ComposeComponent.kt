package com.moriatsushi.koject.compose

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.component.Component

@OptIn(ExperimentalKojectApi::class)
@Component
@MustBeDocumented
@Retention(AnnotationRetention.BINARY)
annotation class ComposeComponent
