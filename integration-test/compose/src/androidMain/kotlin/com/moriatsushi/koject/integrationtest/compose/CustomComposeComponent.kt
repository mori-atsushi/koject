package com.moriatsushi.koject.integrationtest.compose

import android.content.Context
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.component.Component
import com.moriatsushi.koject.component.ComponentExtras
import com.moriatsushi.koject.compose.ComposeContext
import com.moriatsushi.koject.compose.ComposeCoroutineScope
import kotlinx.coroutines.CoroutineScope

@Component
@MustBeDocumented
@Retention(AnnotationRetention.BINARY)
annotation class CustomComposeComponent

@OptIn(ExperimentalKojectApi::class)
internal class CustomComposeComponentExtras(
    @ComposeContext
    val context: Context,
    @ComposeCoroutineScope
    val coroutineScope: CoroutineScope,
) : ComponentExtras<CustomComposeComponent>

@CustomComposeComponent
@Provides
class ForCustomComposeWithContext(
    val applicationContext: Context,
    @ComposeContext
    val composeContext: Context,
)

@CustomComposeComponent
@Provides
class ForCustomComposeWithContext2(
    @ComposeCoroutineScope
    val coroutineScope: CoroutineScope,
)
