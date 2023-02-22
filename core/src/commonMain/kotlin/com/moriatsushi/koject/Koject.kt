package com.moriatsushi.koject

import com.moriatsushi.koject.error.CodeNotGeneratedException
import com.moriatsushi.koject.error.KojectNotStartedException

/**
 * Koject
 *
 * Hold a [Container] in application.
 */
object Koject {
    private var _container: Container? = null

    /**
     * current [Container]
     */
    val container: Container
        get() = _container ?: throw KojectNotStartedException(
            "Koject has not been started.",
        )

    /**
     * Set [container] and start application
     */
    fun start(container: Container) {
        _container = container
    }

    internal fun stop() {
        _container = null
    }
}

/**
 * Start application
 *
 * Will be replaced by automatic code generation.
 * Not actually called.
 */
@Suppress("unused")
fun Koject.start() {
    throw CodeNotGeneratedException(
        "Code generation failed. Check your ksp settings.",
    )
}
