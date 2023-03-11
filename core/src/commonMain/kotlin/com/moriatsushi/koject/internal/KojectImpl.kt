package com.moriatsushi.koject.internal

import com.moriatsushi.koject.error.KojectNotStartedException

internal class KojectImpl {
    private var _container: Container? = null
    val container: Container
        get() = _container ?: throw KojectNotStartedException(
            "Koject has not been started.",
        )

    fun start(container: Container) {
        _container = container
    }

    fun stop() {
        _container = null
    }
}
