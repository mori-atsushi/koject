package com.moriatsushi.koject.integrationtest.app.test

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.integrationtest.app.AppClass1
import com.moriatsushi.koject.integrationtest.app.applyExtras
import com.moriatsushi.koject.test.runTest
import com.moriatsushi.koject.test.startTest
import kotlin.test.Test

class KojectTest {
    @Test
    fun startTest() {
        Koject.startTest {
            applyExtras()
        }
        inject<AppClass1>()
        Koject.stop()
    }

    @Test
    fun runTest() {
        Koject.runTest(
            builder = {
                applyExtras()
            },
        ) {
            inject<AppClass1>()
        }
    }
}
