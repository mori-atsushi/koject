package com.moriatsushi.koject.processor.identifier

import com.moriatsushi.koject.internal.identifier.Identifier
import com.moriatsushi.koject.internal.identifier.of
import kotlin.test.assertEquals
import org.junit.Test

class EscapedValueTest {
    @Test
    fun int() {
        val target = Identifier.of<Int>()
        val actual = target.escapedValue
        val expected = "kotlin_Int"
        assertEquals(expected, actual)
    }

    @Test
    fun list() {
        val target = Identifier.of<List<Int>>()
        val actual = target.escapedValue
        val expected = "kotlin_collections_List__kotlin_Int__"
        assertEquals(expected, actual)
    }

    @Test
    fun map() {
        val target = Identifier.of<Map<Int, String>>()
        val actual = target.escapedValue
        val expected = "kotlin_collections_Map__kotlin_Int__kotlin_String__"
        assertEquals(expected, actual)
    }

    @Test
    fun nested() {
        val target = Identifier.of<Map<Int, Map<Int, Map<Int, String>>>>()
        val actual = target.escapedValue
        val expected = "kotlin_collections_Map__kotlin_Int__kotlin_collections_Map__" +
            "kotlin_Int__kotlin_collections_Map__kotlin_Int__kotlin_String______"
        assertEquals(expected, actual)
    }

    @Test
    fun nullable() {
        val target = Identifier.of<Int?>()
        val actual = target.escapedValue
        val expected = "kotlin_Int_nullable"
        assertEquals(expected, actual)
    }

    @Test
    fun nullableList() {
        val target = Identifier.of<List<String?>?>()
        val actual = target.escapedValue
        val expected = "kotlin_collections_List__kotlin_String_nullable___nullable"
        assertEquals(expected, actual)
    }
}
