package com.moriatsushi.koject.processor.assert

import java.io.File
import kotlin.test.assertTrue

fun assertExistsFile(folder: File, filePath: String) {
    val path = folder.resolve(filePath)
    assertExistsFile(path)
}

fun assertExistsFile(file: File) {
    assertTrue(file.exists(), "$file is not exists")
}
