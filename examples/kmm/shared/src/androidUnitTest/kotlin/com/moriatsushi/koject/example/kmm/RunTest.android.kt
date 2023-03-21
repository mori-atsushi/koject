package com.moriatsushi.koject.example.kmm

import android.app.Application
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.android.application
import com.moriatsushi.koject.test.runTest
import org.mockito.Mockito.mock

actual fun Koject.runTest(block: () -> Unit) {
    Koject.runTest(
        builder = {
            application(mock(Application::class.java))
        },
    ) {
        block()
    }
}
