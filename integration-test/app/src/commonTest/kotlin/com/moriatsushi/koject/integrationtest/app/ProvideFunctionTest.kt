package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.start
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class ProvideFunctionTest {
    @AfterTest
    fun clear() {
        Koject.stop()
    }

    @Test
    fun successInject_byFunction() {
        Koject.start()

        val actual = inject<String>()
        val expected = "provided"
        assertEquals(expected, actual)
    }

    @Test
    fun successInject_interface() {
        Koject.start()

        val actual = inject<ProvidableInterface>()
        assertIs<ProvidableInterface>(actual)
    }

    @Test
    fun successInject_withParameters() {
        Koject.start()

        val actual = inject<ProvideFunctionWithParameters>()
        assertIs<ProvideFunctionWithParameters>(actual)
        assertEquals("provided", actual.string)
    }
}
