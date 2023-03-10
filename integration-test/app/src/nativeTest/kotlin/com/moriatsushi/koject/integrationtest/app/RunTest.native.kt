package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.integrationtest.app.extras.GlobalExtraClass1
import com.moriatsushi.koject.integrationtest.app.extras.GlobalExtraSingleton1
import com.moriatsushi.koject.integrationtest.app.extras.GlobalExtras

@OptIn(ExperimentalKojectApi::class)
actual fun Koject.setExtras() {
    addExtras(GlobalExtras(GlobalExtraClass1(), GlobalExtraSingleton1()))
}
