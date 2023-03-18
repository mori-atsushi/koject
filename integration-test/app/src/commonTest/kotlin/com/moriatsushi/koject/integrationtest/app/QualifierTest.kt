package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.lazyInject
import kotlin.test.Test
import kotlin.test.assertEquals

class QualifierTest {
    @Test
    fun successInject_qualifier() = Koject.runMain {
        val class1 = inject<QualifierClass>(ID1())
        assertEquals("id1", class1.string)

        val class2 = inject<QualifierClass>(ID2())
        assertEquals("id2", class2.string)

        val noName = inject<QualifierClass>()
        assertEquals("not set", noName.string)
    }

    @Test
    fun successLazyInject_qualifier() = Koject.runMain {
        val class1 by lazyInject<QualifierClass>(ID1())
        assertEquals("id1", class1.string)

        val class2 by lazyInject<QualifierClass>(ID2())
        assertEquals("id2", class2.string)

        val noName by lazyInject<QualifierClass>()
        assertEquals("not set", noName.string)
    }

    @Test
    fun successInject_qualifierHolderClass() = Koject.runMain {
        val holder = inject<QualifierHolderClass>()
        assertEquals("not set", holder.notSet.string)
        assertEquals("id1", holder.id1.string)
        assertEquals("id2", holder.id2.string)
    }

    @Test
    fun successInject_qualifierHolderInterface() = Koject.runMain {
        val holder = inject<QualifierHolderInterface>()
        assertEquals("not set", holder.notSet.string)
        assertEquals("id1", holder.id1.string)
        assertEquals("id2", holder.id2.string)
    }
}
