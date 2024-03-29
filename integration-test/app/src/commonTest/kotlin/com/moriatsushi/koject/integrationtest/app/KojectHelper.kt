package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.KojectBuilder
import com.moriatsushi.koject.integrationtest.app.extras.GlobalExtraClass1
import com.moriatsushi.koject.integrationtest.app.extras.GlobalExtraSingleton1
import com.moriatsushi.koject.integrationtest.app.extras.GlobalExtras
import com.moriatsushi.koject.integrationtest.app.extras.WithNamedExtras
import com.moriatsushi.koject.integrationtest.app.extras.WithQualifierExtras
import com.moriatsushi.koject.start

fun Koject.runMain(
    block: () -> Unit,
) {
    start {
        applyExtras()
    }
    block()
    stop()
}

@OptIn(ExperimentalKojectApi::class)
internal fun KojectBuilder.applyExtras() {
    addExtras(GlobalExtras(GlobalExtraClass1(), GlobalExtraSingleton1()))
    addExtras(WithQualifierExtras())
    addExtras(WithNamedExtras())
}
