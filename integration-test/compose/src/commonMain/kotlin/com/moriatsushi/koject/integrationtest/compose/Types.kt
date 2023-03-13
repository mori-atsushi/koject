package com.moriatsushi.koject.integrationtest.compose

import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Singleton
import com.moriatsushi.koject.compose.ComposeComponent
import com.moriatsushi.koject.compose.ComposeCoroutineScope
import kotlinx.coroutines.CoroutineScope

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

@ComposeComponent
@Provides
class ForComposeWithCoroutineScope(
    @ComposeCoroutineScope
    val scope: CoroutineScope,
)
