package com.moriatsushi.koject

import com.moriatsushi.koject.error.CodeNotGeneratedException
import com.moriatsushi.koject.error.KojectNotStartedException
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class KojectTest {
    @AfterTest
    fun clear() {
        Koject.stop()
    }

    @Test
    fun successStart_withContainer() {
        val container = FakeContainer()
        Koject._start(container)
        assertEquals(container, Koject.container)
    }

    @Test
    fun failStart_kspIsDisabled() {
        assertFailsWith<CodeNotGeneratedException> {
            Koject.start()
        }
    }

    @Test
    fun noContainer_notStarted() {
        assertFailsWith<KojectNotStartedException> {
            Koject.container
        }
    }
}
