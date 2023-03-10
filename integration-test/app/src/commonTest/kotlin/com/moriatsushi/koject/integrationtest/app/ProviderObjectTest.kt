package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class ProviderObjectTest {
    @Test
    fun successInject_byObject() = Koject.runTest {
        val actual = inject<Int>()
        val expected = 123
        assertEquals(expected, actual)
    }

    @Test
    fun successInject_withParameters() = Koject.runTest {
        val actual = inject<ProviderObjectWithParameters>()
        assertIs<ProviderObjectWithParameters>(actual)
        assertEquals(123, actual.int)
    }
}
