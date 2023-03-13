package com.moriatsushi.koject.compose

import androidx.compose.runtime.Composable
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.component.ComponentExtras

@OptIn(ExperimentalKojectApi::class)
internal expect class ComposeComponentExtras : ComponentExtras<ComposeComponent>

@Composable
@PublishedApi
internal expect fun composeComponentExtras(): ComposeComponentExtras
