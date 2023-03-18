@file:Suppress("DEPRECATION_ERROR")

package com.moriatsushi.koject.test

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.KojectBuilder
import com.moriatsushi.koject.error.CodeNotGeneratedException
import com.moriatsushi.koject.internal.DoNotUse

/**
 * Start Koject application for test
 *
 * Call [Koject.stop] to stop.
 *
 * Will be replaced by automatic code generation.
 * Not actually called.
 *
 * @param doNotUse Do not use! It's a hack to overwrite with generated code.
 * @param builder Configurations to start.
 */
@Suppress("UNUSED", "UNUSED_PARAMETER")
fun Koject.startTest(
    doNotUse: DoNotUse = DoNotUse,
    builder: KojectBuilder.() -> Unit = {},
) {
    throw CodeNotGeneratedException(
        "Code generation failed. Check your ksp settings.",
    )
}

/**
 * Runs Koject application testing
 *
 * [Koject].[startTest] is called before execution of [block]
 * and [Koject.stop] is called after execution.
 *
 * Will be replaced by automatic code generation.
 * Not actually called.
 *
 * @param builder Configurations to start.
 * @param doNotUse Do not use! It's a hack to overwrite with generated code.
 * @param block Test content.
 */
@Suppress("UNUSED", "UNUSED_PARAMETER")
fun Koject.runTest(
    builder: KojectBuilder.() -> Unit = {},
    doNotUse: DoNotUse = DoNotUse,
    block: () -> Unit,
) {
    throw CodeNotGeneratedException(
        "Code generation failed. Check your ksp settings.",
    )
}
