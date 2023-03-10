package com.moriatsushi.koject.internal

import com.moriatsushi.koject.error.KojectNotStartedException

internal class KojectImpl {
    private var _container: Container? = null
    val container: Container
        get() = _container ?: throw KojectNotStartedException(
            "Koject has not been started.",
        )

    private val extras = mutableSetOf<Any>()

    fun addExtras(extras: Any) {
        this.extras.add(extras)
    }

    fun start(builder: (extras: Set<Any>) -> Container) {
        _container = builder(extras)
        extras.clear()
    }

    fun stop() {
        _container = null
    }
}
