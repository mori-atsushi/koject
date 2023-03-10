@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.integrationtest.app.extras.GlobalExtraClass1
import com.moriatsushi.koject.integrationtest.app.extras.GlobalExtraSingleton1
import com.moriatsushi.koject.integrationtest.app.extras.GlobalExtras
import com.moriatsushi.koject.start

fun Koject.runTest(
    extras: Set<Any> = defaultExtras,
    f: () -> Unit,
) {
    extras.forEach {
        addExtras(it)
    }
    Koject.start()
    f()
    stop()
}

private val defaultExtras = setOf(
    GlobalExtras(GlobalExtraClass1(), GlobalExtraSingleton1()),
)
