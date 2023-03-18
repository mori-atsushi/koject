package com.moriatsushi.koject.integrationtest.app.test

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.integrationtest.app.applyExtras
import com.moriatsushi.koject.test.runTest
import com.moriatsushi.koject.test.startTest
import kotlin.test.Test
import kotlin.test.assertEquals

class KojectTest {
    @Test
    fun startTest() {
        Koject.startTest {
            applyExtras()
        }
        inject<ProvideInTest>()
        inject<TestProvideInTest>()
        Koject.stop()
    }

    @Test
    fun runTest() {
        Koject.runTest(
            builder = {
                applyExtras()
            },
        ) {
            inject<ProvideInTest>()
            inject<TestProvideInTest>()
        }
    }

    @Test
    fun replaceInTest() {
        Koject.runTest(
            builder = {
                applyExtras()
            },
        ) {
            val value = inject<TestInterface>()
            assertEquals("replaced", value.name)

            val holder = inject<TestInterfaceHolder>()
            assertEquals("replaced", holder.value.name)
        }
    }
}
