package com.moriatsushi.koject.compose

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.component.ComponentExtras
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalKojectApi::class)
internal actual class ComposeComponentExtras(
    @ComposeContext
    val context: Context,
    private val coroutineScopeHolder: CoroutineScopeHolder,
) : ComponentExtras<ComposeComponent> {
    @ComposeCoroutineScope
    actual val coroutineScope: CoroutineScope
        get() = coroutineScopeHolder.coroutineScope
}

@PublishedApi
@Composable
internal actual fun composeComponentExtras(): ComposeComponentExtras {
    val context = LocalContext.current
    val coroutineScopeHolder = rememberCoroutineScopeHolder()
    return remember(context) {
        ComposeComponentExtras(context, coroutineScopeHolder)
    }
}
