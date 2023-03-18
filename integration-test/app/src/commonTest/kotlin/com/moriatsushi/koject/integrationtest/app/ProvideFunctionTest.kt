package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class ProvideFunctionTest {
    @Test
    fun successInject_byFunction() = Koject.runMain {
        val actual = inject<String>()
        val expected = "provided"
        assertEquals(expected, actual)
    }

    @Test
    fun successInject_interface() = Koject.runMain {
        val actual = inject<ProvidableInterface>()
        assertIs<ProvidableInterface>(actual)
    }

    @Test
    fun successInject_withParameters() = Koject.runMain {
        val actual = inject<ProvideFunctionWithParameters>()
        assertIs<ProvideFunctionWithParameters>(actual)
        assertEquals("provided", actual.string)
    }
}
