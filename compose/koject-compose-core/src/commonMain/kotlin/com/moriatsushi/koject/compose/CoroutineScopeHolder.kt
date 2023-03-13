package com.moriatsushi.koject.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.RememberObserver
import androidx.compose.runtime.remember
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

internal interface CoroutineScopeHolder {
    val coroutineScope: CoroutineScope
}

private class CoroutineScopeHolderImpl(
    private val contextProducer: () -> CoroutineContext,
) : CoroutineScopeHolder, RememberObserver {
    private var _coroutineScope: CoroutineScope? = null
    override val coroutineScope: CoroutineScope
        get() = _coroutineScope ?: CoroutineScope(contextProducer()).also {
            _coroutineScope = it
        }

    override fun onRemembered() {
        // no op
    }

    override fun onForgotten() {
        _coroutineScope?.cancel()
    }

    override fun onAbandoned() {
        _coroutineScope?.cancel()
    }
}

@Composable
internal fun rememberCoroutineScopeHolder(): CoroutineScopeHolder {
    return remember {
        CoroutineScopeHolderImpl {
            Job() + Dispatchers.Main.immediate
        }
    }
}
