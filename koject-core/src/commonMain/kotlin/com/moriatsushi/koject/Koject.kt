@file:Suppress("DEPRECATION_ERROR")

package com.moriatsushi.koject

import com.moriatsushi.koject.error.CodeNotGeneratedException
import com.moriatsushi.koject.internal.Container
import com.moriatsushi.koject.internal.DoNotUse
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
     * Set [container] and start application
     */
    @Suppress("FunctionName")
    @InternalKojectApi
    fun _start(container: Container) {
        impl.start(container)
    }

    /**
     * Release the current container
     */
    fun stop() {
        impl.stop()
    }
}

/**
 * Start Koject application
 *
 * Will be replaced by automatic code generation.
 * Not actually called.
 *
 * @param doNotUse Do not use! It's a hack to overwrite with generated code.
 * @param builder Configurations to start.
 */
@Suppress("UNUSED", "UNUSED_PARAMETER")
fun Koject.start(
    doNotUse: DoNotUse = DoNotUse,
    builder: KojectBuilder.() -> Unit = {},
) {
    throw CodeNotGeneratedException(
        "Code generation failed. Check your ksp settings.",
    )
}
