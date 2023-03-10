package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.start

fun Koject.runTest(
    preparation: Koject.() -> Unit = { setExtras() },
    block: () -> Unit,
) {
    preparation()
    start()
    block()
    stop()
}

expect fun Koject.setExtras()
