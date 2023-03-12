package com.moriatsushi.koject.integrationtest.compose

import androidx.test.core.app.ApplicationProvider
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.android.application
import com.moriatsushi.koject.start

actual fun Koject.runTest(block: () -> Unit) {
    start {
        application(ApplicationProvider.getApplicationContext())
    }
    block()
    stop()
}
