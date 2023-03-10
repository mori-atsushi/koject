package com.moriatsushi.koject

import com.moriatsushi.koject.error.KojectNotStartedException
import com.moriatsushi.koject.error.NotProvidedException
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class InjectTest {
    @AfterTest
    fun clear() {
        Koject.stop()
    }

    @Test
    fun successInject() {
        Koject._start { FakeContainer() }
        val actual = inject<String>()
        val expected = "test"
        assertEquals(actual, expected)
    }

    @Test
    fun failInject_notProvided() {
        Koject._start { FakeContainer() }
        assertFailsWith<NotProvidedException> {
            inject<Pair<Int, Int>>()
        }
    }

    @Test
    fun failInject_notStarted() {
        assertFailsWith<KojectNotStartedException> {
            inject<String>()
        }
    }
}
