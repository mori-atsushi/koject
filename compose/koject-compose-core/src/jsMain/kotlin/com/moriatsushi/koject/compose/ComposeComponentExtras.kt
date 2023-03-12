package com.moriatsushi.koject.compose

import androidx.compose.runtime.Composable
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.component.ComponentExtras

@OptIn(ExperimentalKojectApi::class)
@ComponentExtras(ComposeComponent::class)
internal actual class ComposeComponentExtras

private val instance = ComposeComponentExtras()

@PublishedApi
@Composable
internal actual fun composeComponentExtras(): ComposeComponentExtras {
    return instance
}
