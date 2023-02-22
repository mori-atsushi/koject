package com.moriatsushi.koject.internal.identifier

import com.moriatsushi.koject.MyClass
import kotlin.test.Test
import kotlin.test.assertEquals

class IdentifierFactoryTest {
    @Test
    fun of_int() {
        val actual = Identifier.of<Int>()
        val expected = Identifier(
            "Int",
        )
        assertEquals(expected, actual)
    }

    @Test
    fun of_list() {
        val actual = Identifier.of<List<Int>>()
        val expected = Identifier(
            "List<Int>",
        )
        assertEquals(expected, actual)
    }

    @Test
    fun of_map() {
        val actual = Identifier.of<Map<Int, String>>()
        val expected = Identifier(
            "Map<Int, String>",
        )
        assertEquals(expected, actual)
    }

    @Test
    fun of_nested() {
        val actual = Identifier.of<Map<Int, Map<Int, Map<Int, String>>>>()
        val expected = Identifier(
            "Map<Int, Map<Int, Map<Int, String>>>",
        )
        assertEquals(expected, actual)
    }

    @Test
    fun of_myClass() {
        val actual = Identifier.of<MyClass>()
        val expected = Identifier(
            "MyClass",
        )
        assertEquals(expected, actual)
    }

    @Test
    fun of_nullable() {
        val actual = Identifier.of<Int?>()
        val expected = Identifier(
            "Int?",
        )
        assertEquals(expected, actual)
    }

    @Test
    fun of_nullableList() {
        val actual = Identifier.of<List<String?>?>()
        val expected = Identifier(
            "List<String?>?",
        )
        assertEquals(expected, actual)
    }
}
