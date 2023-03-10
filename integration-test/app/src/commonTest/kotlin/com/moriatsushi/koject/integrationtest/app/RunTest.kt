package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.integrationtest.app.extras.GlobalExtraClass1
import com.moriatsushi.koject.integrationtest.app.extras.GlobalExtraSingleton1
import com.moriatsushi.koject.integrationtest.app.extras.GlobalExtras
import com.moriatsushi.koject.integrationtest.app.extras.WithQualifierExtras
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

@OptIn(ExperimentalKojectApi::class)
fun Koject.addCommonExtras() {
    addExtras(GlobalExtras(GlobalExtraClass1(), GlobalExtraSingleton1()))
    addExtras(WithQualifierExtras())
}

expect fun Koject.setExtras()
