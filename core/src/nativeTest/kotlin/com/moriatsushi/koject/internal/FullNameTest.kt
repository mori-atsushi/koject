package com.moriatsushi.koject.internal

import com.moriatsushi.koject.MyClass
import com.moriatsushi.koject.MyInterface
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class FullNameTest {
    @Test
    fun int() {
        val target = Int::class
        val actual = target.fullName
        val expected = "kotlin.Int"
        assertEquals(expected, actual)
    }

    @Test
    fun list() {
        val target = List::class
        val actual = target.fullName
        val expected = "kotlin.collections.List"
        assertEquals(expected, actual)
    }

    @Test
    fun myClass() {
        val target = MyClass::class
        val actual = target.fullName
        val expected = "com.moriatsushi.koject.MyClass"
        assertEquals(expected, actual)
    }

    @Test
    fun notSupported_anonymous() {
        val anonymous = object : MyInterface {}
        val target = anonymous::class
        assertFails {
            target.fullName
        }
    }
}
