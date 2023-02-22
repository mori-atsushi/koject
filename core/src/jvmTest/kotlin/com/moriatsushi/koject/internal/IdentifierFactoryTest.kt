package com.moriatsushi.koject.internal

import com.moriatsushi.koject.MyClass
import com.moriatsushi.koject.identifier.Identifier
import kotlin.test.assertEquals
import org.junit.Test

class IdentifierFactoryTest {
    @Test
    fun of_int() {
        val actual = Identifier.of<Int>()
        val expected = Identifier(
            "kotlin.Int",
        )
        assertEquals(expected, actual)
    }

    @Test
    fun of_list() {
        val actual = Identifier.of<List<Int>>()
        val expected = Identifier(
            "kotlin.collections.List<kotlin.Int>",
        )
        assertEquals(expected, actual)
    }

    @Test
    fun of_map() {
        val actual = Identifier.of<Map<Int, String>>()
        val expected = Identifier(
            "kotlin.collections.Map<kotlin.Int, kotlin.String>",
        )
        assertEquals(expected, actual)
    }

    @Test
    fun of_nested() {
        val actual = Identifier.of<Map<Int, Map<Int, Map<Int, String>>>>()
        val expected = Identifier(
            "kotlin.collections.Map<kotlin.Int, kotlin.collections.Map" +
                "<kotlin.Int, kotlin.collections.Map<kotlin.Int, kotlin.String>>>",
        )
        assertEquals(expected, actual)
    }

    @Test
    fun of_myClass() {
        val actual = Identifier.of<MyClass>()
        val expected = Identifier(
            "com.moriatsushi.koject.MyClass",
        )
        assertEquals(expected, actual)
    }

    @Test
    fun of_nullable() {
        val actual = Identifier.of<Int?>()
        val expected = Identifier(
            "kotlin.Int?",
        )
        assertEquals(expected, actual)
    }

    @Test
    fun of_nullableList() {
        val actual = Identifier.of<List<String?>?>()
        val expected = Identifier(
            "kotlin.collections.List<kotlin.String?>?",
        )
        assertEquals(expected, actual)
    }
}
