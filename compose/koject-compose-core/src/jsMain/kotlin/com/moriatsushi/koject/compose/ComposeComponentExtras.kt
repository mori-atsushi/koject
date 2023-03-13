package com.moriatsushi.koject.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.component.ComponentExtras
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalKojectApi::class)
internal actual class ComposeComponentExtras(
    private val coroutineScopeHolder: CoroutineScopeHolder,
) : ComponentExtras<ComposeComponent> {
    @ComposeCoroutineScope
    actual val coroutineScope: CoroutineScope
        get() = coroutineScopeHolder.coroutineScope
}

@PublishedApi
@Composable
internal actual fun composeComponentExtras(): ComposeComponentExtras {
    val coroutineScopeHolder = rememberCoroutineScopeHolder()
    return remember {
        ComposeComponentExtras(coroutineScopeHolder)
    }
}
