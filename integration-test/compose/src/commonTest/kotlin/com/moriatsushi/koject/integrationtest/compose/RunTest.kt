package com.moriatsushi.koject.integrationtest.compose

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.start

fun Koject.runTest(
    block: () -> Unit,
) {
    start()
    block()
    stop()
}
