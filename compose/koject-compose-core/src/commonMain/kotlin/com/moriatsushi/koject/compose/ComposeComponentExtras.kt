package com.moriatsushi.koject.compose

import androidx.compose.runtime.Composable

internal expect class ComposeComponentExtras

@Composable
@PublishedApi
internal expect fun composeComponentExtras(): ComposeComponentExtras
