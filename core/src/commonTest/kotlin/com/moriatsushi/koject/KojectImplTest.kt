package com.moriatsushi.koject

import com.moriatsushi.koject.error.KojectNotStartedException
import com.moriatsushi.koject.internal.KojectImpl
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class KojectImplTest {
    @Test
    fun successStart_withContainer() {
        val subject = KojectImpl()
        val container = FakeContainer()
        subject.start { container }
        assertEquals(container, subject.container)
    }

    @Test
    fun getSpecifiedExtras() {
        val subject = KojectImpl()
        subject.addExtras("extra1")
        subject.addExtras("extra2")

        subject.start {
            it.contains("extra1")
            it.contains("extra2")
            FakeContainer()
        }
    }

    @Test
    fun clearExtrasAfterStart() {
        val subject = KojectImpl()
        subject.addExtras("extra1")
        subject.addExtras("extra2")

        subject.start {
            it.contains("extra1")
            it.contains("extra2")
            FakeContainer()
        }

        subject.start {
            it.isEmpty()
            FakeContainer()
        }
    }

    @Test
    fun noContainer_notStarted() {
        val subject = KojectImpl()
        assertFailsWith<KojectNotStartedException> {
            subject.container
        }
    }
}
