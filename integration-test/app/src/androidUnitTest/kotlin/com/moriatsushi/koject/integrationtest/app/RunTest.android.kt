package com.moriatsushi.koject.integrationtest.app

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.android.application
import org.mockito.kotlin.mock

private val mockApplication: Application by lazy { mock() }

actual fun Koject.setExtras() {
    application(mockApplication)
    addCommonExtras()
}

fun Koject.runAndroidTest(block: () -> Unit) {
    runTest(
        preparation = {
            application(ApplicationProvider.getApplicationContext())
            addCommonExtras()
        },
        block = block,
    )
}
