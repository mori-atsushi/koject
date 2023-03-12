package com.moriatsushi.koject.integrationtest.compose

import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Singleton
import com.moriatsushi.koject.compose.ComposeComponent

@Provides
class CommonClass

@Singleton
@Provides
class CommonSingleton

@ComposeComponent
@Provides
class ForComposeClass

@ComposeComponent
@Provides
class ForComposeHolder(
    value: ForComposeClass,
)
