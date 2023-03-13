package com.moriatsushi.koject.android.viewmodel

import java.io.Closeable
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

internal class CoroutineScopeHolder(
    private val contextProducer: () -> CoroutineContext,
) : Closeable {
    private var _coroutineScope: CoroutineScope? = null
    val coroutineScope: CoroutineScope
        get() = _coroutineScope ?: CoroutineScope(contextProducer()).also {
            _coroutineScope = it
        }

    override fun close() {
        _coroutineScope?.cancel()
    }
}
