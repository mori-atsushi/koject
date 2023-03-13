package com.moriatsushi.koject.compose

import androidx.compose.runtime.Composable
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.component.ComponentExtras

@OptIn(ExperimentalKojectApi::class)
internal actual class ComposeComponentExtras : ComponentExtras<ComposeComponent>

private val instance = ComposeComponentExtras()

@PublishedApi
@Composable
internal actual fun composeComponentExtras(): ComposeComponentExtras {
    return instance
}
