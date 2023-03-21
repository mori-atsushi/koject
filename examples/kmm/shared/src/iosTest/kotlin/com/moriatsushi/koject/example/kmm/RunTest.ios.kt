package com.moriatsushi.koject.example.kmm

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.test.runTest

actual fun Koject.runTest(block: () -> Unit) {
    Koject.runTest(builder = {}) {
        block()
    }
}
