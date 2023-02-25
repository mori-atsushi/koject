package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.error.NotProvidedException
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.start
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class NamedTest {
    @AfterTest
    fun clear() {
        Koject.stop()
    }

    @Test
    fun successInject_namedClass() {
        Koject.start()

        val class1 = inject<NamedClass>("name1")
        assertEquals("name1", class1.name)

        val class2 = inject<NamedClass>("name2")
        assertEquals("name2", class2.name)

        val noName = inject<NamedClass>()
        assertEquals("no name", noName.name)
    }

    @Test
    fun successInject_namedInterface() {
        Koject.start()

        val interface1 = inject<NamedInterface>("name1")
        assertEquals("name1", interface1.name)

        val interface2 = inject<NamedInterface>("name2")
        assertEquals("name2", interface2.name)
    }

    @Test
    fun failedInject_notProvidedName() {
        Koject.start()

        assertFailsWith<NotProvidedException> {
            inject<NamedInterface>()
        }
    }

    @Test
    fun successInject_namedHolderClass() {
        Koject.start()

        val holder = inject<NamedHolderClass>()
        assertEquals("name1", holder.class1.name)
        assertEquals("name2", holder.class2.name)
        assertEquals("no name", holder.noName.name)
        assertEquals("name1", holder.interface1.name)
        assertEquals("name2", holder.interface2.name)
    }
}
