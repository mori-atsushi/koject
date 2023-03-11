package com.moriatsushi.koject

import com.moriatsushi.koject.error.CodeNotGeneratedException
import com.moriatsushi.koject.extras.KojectExtras
import com.moriatsushi.koject.internal.Container
import com.moriatsushi.koject.internal.InternalKojectApi
import com.moriatsushi.koject.internal.KojectImpl

/**
 * Koject
 *
 * Hold a [Container] in application.
 */
object Koject {
    private val impl = KojectImpl()

    /**
     * current [Container]
     */
    internal val container: Container
        get() = impl.container

    /**
     * Add extra dependencies before starting Koject.
     *
     * @param extras [KojectExtras] class
     */
    @ExperimentalKojectApi
    fun addExtras(extras: Any) {
        impl.addExtras(extras)
    }

    /**
     * Set [container] and start application
     */
    @Suppress("FunctionName")
    @InternalKojectApi
    fun _start(container: Container) {
        impl.start { container }
    }

    /**
     * Set [container] with extras and start application
     */
    @Suppress("FunctionName")
    @InternalKojectApi
    fun _start(builder: (extras: Set<Any>) -> Container) {
        impl.start(builder)
    }

    /**
     * Release the current container
     */
    fun stop() {
        impl.stop()
    }
}

/**
 * Start application
 *
 * Will be replaced by automatic code generation.
 * Not actually called.
 *
 * @param nothing Do not use.
 * @param builder Configurations to start
 */
@Suppress("UNUSED", "UNUSED_PARAMETER")
fun Koject.start(
    nothing: Nothing = codeNotGeneratedError(),
    builder: KojectBuilder.() -> Unit = {}
) {
    codeNotGeneratedError()
}

private fun codeNotGeneratedError(): Nothing {
    throw CodeNotGeneratedException(
        "Code generation failed. Check your ksp settings.",
    )
}
