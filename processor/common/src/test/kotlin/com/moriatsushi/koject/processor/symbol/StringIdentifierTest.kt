package com.moriatsushi.koject.processor.symbol

import com.moriatsushi.koject.internal.identifier.StringIdentifier
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.asTypeName
import kotlin.reflect.typeOf
import kotlin.test.assertEquals
import org.junit.Test

class StringIdentifierTest {
    @Test
    fun asCodeName_int() {
        val typeName = Int::class.asTypeName()
        val target = StringIdentifier.of(typeName)
        val actual = target.asCodeName()
        val expected = "kotlin_Int"
        assertEquals(expected, actual)
    }

    @Test
    fun asCodeName_list() {
        val typeName = typeOf<List<Int>>().asTypeName()
        val target = StringIdentifier.of(typeName)
        val actual = target.asCodeName()
        val expected = "kotlin_collections_List__kotlin_Int__"
        assertEquals(expected, actual)
    }

    @Test
    fun asCodeName_map() {
        val typeName = typeOf<Map<Int, String>>().asTypeName()
        val target = StringIdentifier.of(typeName)
        val actual = target.asCodeName()
        val expected = "kotlin_collections_Map__kotlin_Int__kotlin_String__"
        assertEquals(expected, actual)
    }

    @Test
    fun asCodeName_nested() {
        val typeName = typeOf<Map<Int, Map<Int, Map<Int, String>>>>().asTypeName()
        val target = StringIdentifier.of(typeName)
        val actual = target.asCodeName()
        val expected = "kotlin_collections_Map__kotlin_Int__kotlin_collections_Map__" +
            "kotlin_Int__kotlin_collections_Map__kotlin_Int__kotlin_String______"
        assertEquals(expected, actual)
    }

    @Test
    fun asCodeName_nullable() {
        val typeName = typeOf<Int?>().asTypeName()
        val target = StringIdentifier.of(typeName)
        val actual = target.asCodeName()
        val expected = "kotlin_Int_nullable"
        assertEquals(expected, actual)
    }

    @Test
    fun asCodeName_nullableList() {
        val typeName = typeOf<List<String?>?>().asTypeName()
        val target = StringIdentifier.of(typeName)
        val actual = target.asCodeName()
        val expected = "kotlin_collections_List__kotlin_String_nullable___nullable"
        assertEquals(expected, actual)
    }

    @Test
    fun asCodeName_named() {
        val typeName = Int::class.asTypeName()
        val qualifier = QualifierAnnotation("Named(name)", CodeBlock.of("Named(name)"))
        val target = StringIdentifier.of(typeName, qualifier)
        val actual = target.asCodeName()
        val expected = "kotlin_Int__0GxX6UJlK7NHytJ_"
        assertEquals(expected, actual)
    }
}
