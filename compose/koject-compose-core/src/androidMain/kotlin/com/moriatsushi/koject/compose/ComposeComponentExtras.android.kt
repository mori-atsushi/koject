package com.moriatsushi.koject.compose

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.component.ComponentExtras

@OptIn(ExperimentalKojectApi::class)
internal actual class ComposeComponentExtras(
    @ComposeContext
    val context: Context,
) : ComponentExtras<ComposeComponent>

@PublishedApi
@Composable
internal actual fun composeComponentExtras(): ComposeComponentExtras {
    val context = LocalContext.current
    return remember(context) {
        ComposeComponentExtras(context)
    }
}
