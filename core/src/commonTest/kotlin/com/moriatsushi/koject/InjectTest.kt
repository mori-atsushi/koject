package com.moriatsushi.koject

import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class InjectTest {
    @AfterTest
    fun clear() {
        Koject.stop()
    }

    @Test
    fun successInject() {
        Koject.start(FakeContainer())
        val actual = inject<String>()
        val expected = "test"
        assertEquals(actual, expected)
    }

    @Test
    fun failInject_notProvided() {
        Koject.start(FakeContainer())
        assertFails {
            inject<Pair<Int, Int>>()
        }
    }
}
