package com.moriatsushi.koject.processor.assert

import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

fun assertFileExists(file: File) {
    assertTrue(file.exists(), "$file is not exists")
}

fun assertFileNotExists(file: File) {
    assertFalse(file.exists(), "$file is exists")
}

fun assertFileTextEquals(expected: String, file: File) {
    assertEquals(expected, file.readText())
}
