package com.moriatsushi.koject.processor.symbol

import kotlin.reflect.typeOf
import kotlin.test.assertEquals
import org.junit.Test

class IdentifierTest {
    @Test
    fun asCodeName_int() {
        val target = Identifier(typeOf<Int>().toString())
        val actual = target.asCodeName()
        val expected = "kotlin_Int"
        assertEquals(expected, actual)
    }

    @Test
    fun asCodeName_list() {
        val target = Identifier(typeOf<List<Int>>().toString())
        val actual = target.asCodeName()
        val expected = "kotlin_collections_List__kotlin_Int__"
        assertEquals(expected, actual)
    }

    @Test
    fun asCodeName_map() {
        val target = Identifier(typeOf<Map<Int, String>>().toString())
        val actual = target.asCodeName()
        val expected = "kotlin_collections_Map__kotlin_Int__kotlin_String__"
        assertEquals(expected, actual)
    }

    @Test
    fun asCodeName_nested() {
        val target = Identifier(typeOf<Map<Int, Map<Int, Map<Int, String>>>>().toString())
        val actual = target.asCodeName()
        val expected = "kotlin_collections_Map__kotlin_Int__kotlin_collections_Map__" +
            "kotlin_Int__kotlin_collections_Map__kotlin_Int__kotlin_String______"
        assertEquals(expected, actual)
    }

    @Test
    fun asCodeName_nullable() {
        val target = Identifier(typeOf<Int?>().toString())
        val actual = target.asCodeName()
        val expected = "kotlin_Int_nullable"
        assertEquals(expected, actual)
    }

    @Test
    fun asCodeName_nullableList() {
        val target = Identifier(typeOf<List<String?>?>().toString())
        val actual = target.asCodeName()
        val expected = "kotlin_collections_List__kotlin_String_nullable___nullable"
        assertEquals(expected, actual)
    }

    @Test
    fun asCodeName_named() {
        val target = Identifier("${typeOf<Int>()}:Named(name)")
        val actual = target.asCodeName()
        val expected = "kotlin_Int__0GxX6UJlK7NHytJ_"
        assertEquals(expected, actual)
    }
}
