package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.start
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class ProviderObjectTest {
    @AfterTest
    fun clear() {
        Koject.stop()
    }

    @Test
    fun successInject_byObject() {
        Koject.start()

        val actual = inject<Int>()
        val expected = 123
        assertEquals(expected, actual)
    }

    @Test
    fun successInject_withParameters() {
        Koject.start()

        val actual = inject<ProviderObjectWithParameters>()
        assertIs<ProviderObjectWithParameters>(actual)
        assertEquals(123, actual.int)
    }
}
