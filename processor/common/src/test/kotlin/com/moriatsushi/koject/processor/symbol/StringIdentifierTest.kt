package com.moriatsushi.koject.processor.symbol

import com.moriatsushi.koject.internal.StringIdentifier
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.asTypeName
import kotlin.reflect.typeOf
import kotlin.test.assertEquals
import org.junit.Test

class StringIdentifierTest {
    @Test
    fun int() {
        val typeName = Int::class.asTypeName()
        val target = StringIdentifier.of(typeName)
        assertEquals(
            expected = "kotlin.Int",
            actual = target.displayName,
        )
        assertEquals(
            expected = "kotlin_Int",
            actual = target.asCodeName(),
        )
    }

    @Test
    fun list() {
        val typeName = typeOf<List<Int>>().asTypeName()
        val target = StringIdentifier.of(typeName)
        assertEquals(
            expected = "kotlin.collections.List<kotlin.Int>",
            actual = target.displayName,
        )
        assertEquals(
            expected = "kotlin_collections_List__kotlin_Int__",
            actual = target.asCodeName(),
        )
    }

    @Test
    fun asCodeName_map() {
        val typeName = typeOf<Map<Int, String>>().asTypeName()
        val target = StringIdentifier.of(typeName)
        assertEquals(
            expected = "kotlin.collections.Map<kotlin.Int, kotlin.String>",
            actual = target.displayName,
        )
        assertEquals(
            expected = "kotlin_collections_Map__kotlin_Int__kotlin_String__",
            actual = target.asCodeName(),
        )
    }

    @Test
    fun nested() {
        val typeName = typeOf<Map<Int, Map<Int, Map<Int, String>>>>().asTypeName()
        val target = StringIdentifier.of(typeName)
        assertEquals(
            expected = "kotlin.collections.Map<kotlin.Int, kotlin.collections.Map" +
                "<kotlin.Int, kotlin.collections.Map<kotlin.Int, kotlin.String>>>",
            actual = target.displayName,
        )
        assertEquals(
            expected = "kotlin_collections_Map__kotlin_Int__kotlin_collections_Map__" +
                "kotlin_Int__kotlin_collections_Map__kotlin_Int__kotlin_String______",
            actual = target.asCodeName(),
        )
    }

    @Test
    fun nullable() {
        val typeName = typeOf<Int?>().asTypeName()
        val target = StringIdentifier.of(typeName)
        assertEquals(
            expected = "kotlin.Int?",
            actual = target.displayName,
        )
        assertEquals(
            expected = "kotlin_Int_nullable",
            actual = target.asCodeName(),
        )
    }

    @Test
    fun nullableList() {
        val typeName = typeOf<List<String?>?>().asTypeName()
        val target = StringIdentifier.of(typeName)
        assertEquals(
            expected = "kotlin.collections.List<kotlin.String?>?",
            actual = target.displayName,
        )
        assertEquals(
            expected = "kotlin_collections_List__kotlin_String_nullable___nullable",
            actual = target.asCodeName(),
        )
    }

    @Test
    fun includeKeyword() {
        val target = StringIdentifier.of(
            ClassName("com.internal.data", "SampleClass"),
        )
        assertEquals(
            expected = "com.`internal`.`data`.SampleClass",
            actual = target.displayName,
        )
        assertEquals(
            expected = "com_internal_data_SampleClass",
            actual = target.asCodeName(),
        )
    }

    @Test
    fun named() {
        val typeName = Int::class.asTypeName()
        val qualifier = QualifierAnnotation("Named(name)", CodeBlock.of("Named(name)"))
        val target = StringIdentifier.of(typeName, qualifier)
        assertEquals(
            expected = "kotlin.Int@Named(name)",
            actual = target.displayName,
        )
        assertEquals(
            expected = "kotlin_Int__0GxX6UJlK7NHytJ_",
            actual = target.asCodeName(),
        )
    }
}
