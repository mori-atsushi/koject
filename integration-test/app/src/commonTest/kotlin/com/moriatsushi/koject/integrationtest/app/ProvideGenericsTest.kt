package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.error.NotProvidedException
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.start
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertIs

class ProvideGenericsTest {
    @AfterTest
    fun clear() {
        Koject.stop()
    }

    @Test
    fun successInject_stringList() {
        Koject.start()

        val actual = inject<List<String>>()
        assertIs<List<String>>(actual)
        assertEquals(listOf("provided"), actual)
    }

    @Test
    fun successInject_intList() {
        Koject.start()

        val actual = inject<List<Int>>()
        assertIs<List<Int>>(actual)
        assertEquals(listOf(123), actual)
    }

    @Test
    fun successInject_otherList() {
        Koject.start()

        assertFailsWith<NotProvidedException> {
            inject<List<Boolean>>()
        }
    }

    @Test
    fun successInject_map() {
        Koject.start()

        val actual = inject<Map<String, String>>()
        assertIs<Map<String, String>>(actual)
        val expected = mapOf("provided" to "provided")
        assertEquals(expected, actual)
    }
}
