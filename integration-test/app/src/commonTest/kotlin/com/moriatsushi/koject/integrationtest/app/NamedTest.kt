package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.Named
import com.moriatsushi.koject.error.NotProvidedException
import com.moriatsushi.koject.inject
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class NamedTest {
    @Test
    fun successInject_namedClass() = Koject.runMain {
        val class1 = inject<NamedClass>(Named("name1"))
        assertEquals("name1", class1.name)

        val class2 = inject<NamedClass>(Named("name2"))
        assertEquals("name2", class2.name)

        val noName = inject<NamedClass>()
        assertEquals("no name", noName.name)
    }

    @Test
    fun successInject_namedInterface() = Koject.runMain {
        val interface1 = inject<NamedInterface>(Named("name1"))
        assertEquals("name1", interface1.name)

        val interface2 = inject<NamedInterface>(Named("name2"))
        assertEquals("name2", interface2.name)
    }

    @Test
    fun successInject_namedClass_legacy() = Koject.runMain {
        val class1 = inject<NamedClass>("name1")
        assertEquals("name1", class1.name)

        val class2 = inject<NamedClass>("name2")
        assertEquals("name2", class2.name)

        val noName = inject<NamedClass>()
        assertEquals("no name", noName.name)
    }

    @Test
    fun successInject_namedInterface_legacy() = Koject.runMain {
        val interface1 = inject<NamedInterface>("name1")
        assertEquals("name1", interface1.name)

        val interface2 = inject<NamedInterface>("name2")
        assertEquals("name2", interface2.name)
    }

    @Test
    fun failedInject_notProvidedName() = Koject.runMain {
        assertFailsWith<NotProvidedException> {
            inject<NamedInterface>()
        }
    }

    @Test
    fun successInject_namedHolderClass() = Koject.runMain {
        val holder = inject<NamedHolderClass>()
        assertEquals("name1", holder.class1.name)
        assertEquals("name2", holder.class2.name)
        assertEquals("no name", holder.noName.name)
        assertEquals("name1", holder.interface1.name)
        assertEquals("name2", holder.interface2.name)
    }
}
