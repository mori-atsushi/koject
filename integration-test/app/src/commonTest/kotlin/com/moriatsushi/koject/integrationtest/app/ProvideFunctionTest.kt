package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class ProvideFunctionTest {
    @Test
    fun successInject_byFunction() = Koject.runTest {
        val actual = inject<String>()
        val expected = "provided"
        assertEquals(expected, actual)
    }

    @Test
    fun successInject_interface() = Koject.runTest {
        val actual = inject<ProvidableInterface>()
        assertIs<ProvidableInterface>(actual)
    }

    @Test
    fun successInject_withParameters() = Koject.runTest {
        val actual = inject<ProvideFunctionWithParameters>()
        assertIs<ProvideFunctionWithParameters>(actual)
        assertEquals("provided", actual.string)
    }
}
