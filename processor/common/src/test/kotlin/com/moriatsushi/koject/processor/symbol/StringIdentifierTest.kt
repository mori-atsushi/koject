package com.moriatsushi.koject.processor.symbol

import com.moriatsushi.koject.internal.identifier.StringIdentifier
import kotlin.reflect.typeOf
import kotlin.test.assertEquals
import org.junit.Test

class StringIdentifierTest {
    @Test
    fun asCodeName_int() {
        val target = StringIdentifier(typeOf<Int>().toString())
        val actual = target.asCodeName()
        val expected = "kotlin_Int"
        assertEquals(expected, actual)
    }

    @Test
    fun asCodeName_list() {
        val target = StringIdentifier(typeOf<List<Int>>().toString())
        val actual = target.asCodeName()
        val expected = "kotlin_collections_List__kotlin_Int__"
        assertEquals(expected, actual)
    }

    @Test
    fun asCodeName_map() {
        val target = StringIdentifier(typeOf<Map<Int, String>>().toString())
        val actual = target.asCodeName()
        val expected = "kotlin_collections_Map__kotlin_Int__kotlin_String__"
        assertEquals(expected, actual)
    }

    @Test
    fun asCodeName_nested() {
        val target = StringIdentifier(typeOf<Map<Int, Map<Int, Map<Int, String>>>>().toString())
        val actual = target.asCodeName()
        val expected = "kotlin_collections_Map__kotlin_Int__kotlin_collections_Map__" +
            "kotlin_Int__kotlin_collections_Map__kotlin_Int__kotlin_String______"
        assertEquals(expected, actual)
    }

    @Test
    fun asCodeName_nullable() {
        val target = StringIdentifier(typeOf<Int?>().toString())
        val actual = target.asCodeName()
        val expected = "kotlin_Int_nullable"
        assertEquals(expected, actual)
    }

    @Test
    fun asCodeName_nullableList() {
        val target = StringIdentifier(typeOf<List<String?>?>().toString())
        val actual = target.asCodeName()
        val expected = "kotlin_collections_List__kotlin_String_nullable___nullable"
        assertEquals(expected, actual)
    }

    @Test
    fun asCodeName_named() {
        val target = StringIdentifier("${typeOf<Int>()}", "Named(name)")
        val actual = target.asCodeName()
        val expected = "kotlin_Int__0GxX6UJlK7NHytJ_"
        assertEquals(expected, actual)
    }
}
