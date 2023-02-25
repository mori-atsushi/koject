package com.moriatsushi.koject.processor.assert

import com.tschuchort.compiletesting.KotlinCompilation
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

fun assertCompileSucceed(result: KotlinCompilation.Result) {
    assertEquals(
        KotlinCompilation.ExitCode.OK,
        result.exitCode,
        "Expected compile succeed",
    )
}

fun assertCompileFailed(result: KotlinCompilation.Result) {
    assertNotEquals(
        KotlinCompilation.ExitCode.OK,
        result.exitCode,
        "Expected compile failed",
    )
}
