package com.moriatsushi.koject.integrationtest.android

import androidx.test.core.app.ApplicationProvider
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.android.application
import com.moriatsushi.koject.start

fun Koject.runTest(
    block: () -> Unit,
) {
    application(ApplicationProvider.getApplicationContext())
    start()
    block()
    stop()
}
