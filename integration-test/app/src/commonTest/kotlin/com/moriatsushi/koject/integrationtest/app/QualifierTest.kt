package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.start
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals

class QualifierTest {
    @AfterTest
    fun clear() {
        Koject.stop()
    }

    @Test
    fun successInject_qualifier() {
        Koject.start()

        val class1 = inject<QualifierClass>(ID1())
        assertEquals("name1", class1.string)

        val class2 = inject<QualifierClass>(ID2())
        assertEquals("name2", class2.string)

        val noName = inject<QualifierClass>()
        assertEquals("not set", noName.string)
    }

    @Test
    fun successInject_qualifierHolderClass() {
        Koject.start()

        val holder = inject<QualifierHolderClass>()
        assertEquals("id1", holder.notSet.string)
        assertEquals("id2", holder.id1.string)
        assertEquals("not set", holder.id2.string)
    }

    @Test
    fun successInject_qualifierHolderInterface() {
        Koject.start()

        val holder = inject<QualifierHolderInterface>()
        assertEquals("id1", holder.notSet.string)
        assertEquals("id2", holder.id1.string)
        assertEquals("not set", holder.id2.string)
    }
}
