package com.moriatsushi.koject.integrationtest.app

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.android.application
import com.moriatsushi.koject.integrationtest.app.extras.GlobalExtraClass1
import com.moriatsushi.koject.integrationtest.app.extras.GlobalExtraSingleton1
import com.moriatsushi.koject.integrationtest.app.extras.GlobalExtras
import org.mockito.kotlin.mock

private val mockApplication: Application by lazy { mock() }

@OptIn(ExperimentalKojectApi::class)
actual fun Koject.setExtras() {
    application(mockApplication)
    addExtras(GlobalExtras(GlobalExtraClass1(), GlobalExtraSingleton1()))
}

@OptIn(ExperimentalKojectApi::class)
fun Koject.runAndroidTest(block: () -> Unit) {
    runTest(
        preparation = {
            application(ApplicationProvider.getApplicationContext())
            addExtras(GlobalExtras(GlobalExtraClass1(), GlobalExtraSingleton1()))
        },
        block = block,
    )
}
