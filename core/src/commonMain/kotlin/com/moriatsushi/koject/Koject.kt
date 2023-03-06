package com.moriatsushi.koject

import com.moriatsushi.koject.error.CodeNotGeneratedException
import com.moriatsushi.koject.error.KojectNotStartedException
import com.moriatsushi.koject.internal.Container
import com.moriatsushi.koject.internal.InternalKojectApi

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
    internal val container: Container
        get() = _container ?: throw KojectNotStartedException(
            "Koject has not been started.",
        )

    /**
     * Set [container] and start application
     */
    @InternalKojectApi
    fun _start(container: Container) {
        _container = container
    }

    /**
     * Release the current container
     */
    fun stop() {
        _container = null
    }
}

/**
 * Start application
 *
 * Will be replaced by automatic code generation.
 * Not actually called.
 */
@Suppress("UNUSED", "UNUSED_PARAMETER")
fun Koject.start(
    nothing: Nothing = codeNotGeneratedError(),
) {
    codeNotGeneratedError()
}

/**
 * Start application (experimental)
 *
 * Will be replaced by automatic code generation.
 * Not actually called.
 *
 * @param extras [Dynamic] dependencies
 */
@ExperimentalKojectApi
@Suppress("UNUSED", "UNUSED_PARAMETER")
fun Koject.start(
    extras: Extras = Extras.empty,
    nothing: Nothing = codeNotGeneratedError(),
) {
    codeNotGeneratedError()
}

/**
 * Start application (experimental)
 *
 * Will be replaced by automatic code generation.
 * Not actually called.
 *
 * @param extras [Dynamic] dependencies
 */
@ExperimentalKojectApi
@Suppress("UNUSED", "UNUSED_PARAMETER")
fun Koject.start(
    nothing: Nothing = codeNotGeneratedError(),
    extras: ExtrasBuilder.() -> Unit,
) {
    codeNotGeneratedError()
}

private fun codeNotGeneratedError(): Nothing {
    throw CodeNotGeneratedException(
        "Code generation failed. Check your ksp settings.",
    )
}
