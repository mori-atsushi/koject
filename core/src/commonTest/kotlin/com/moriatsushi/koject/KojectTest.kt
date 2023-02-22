package com.moriatsushi.koject

import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class KojectTest {
    @AfterTest
    fun clear() {
        Koject.stop()
    }

    @Test
    fun successStart_withContainer() {
        val container = FakeContainer()
        Koject.start(container)
        assertEquals(container, Koject.container)
    }

    @Test
    fun failStart_kspIsDisabled() {
        assertFails {
            Koject.start()
        }
    }

    @Test
    fun noContainer_notStarted() {
        assertFails {
            Koject.container
        }
    }
}
