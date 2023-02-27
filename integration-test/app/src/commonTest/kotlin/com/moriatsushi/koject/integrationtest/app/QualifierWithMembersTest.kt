package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.start
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals

class QualifierWithMembersTest {
    @AfterTest
    fun clear() {
        Koject.stop()
    }

    @Test
    fun successInject_stringQualifier() {
        Koject.start()

        val value1 = inject<String>(StringQualifier("id1"))
        assertEquals("StringQualifier-id1", value1)

        val value2 = inject<String>(StringQualifier("id2"))
        assertEquals("StringQualifier-id2", value2)

        val valueDefault = inject<String>(StringQualifier())
        assertEquals("StringQualifier-default", valueDefault)
    }

    @Test
    fun successInject_enumQualifier() {
        Koject.start()

        val value1 = inject<String>(EnumQualifier(QualifierEnum.ID1))
        assertEquals("EnumQualifier-id1", value1)

        val value2 = inject<String>(EnumQualifier(QualifierEnum.ID2))
        assertEquals("EnumQualifier-id2", value2)
    }

    @Test
    fun successInject_classQualifier() {
        Koject.start()

        val value1 = inject<String>(ClassQualifier(String::class))
        assertEquals("ClassQualifier-string", value1)

        val value2 = inject<String>(ClassQualifier(Int::class))
        assertEquals("ClassQualifier-int", value2)
    }

    @Test
    fun successInject_multipleMemberQualifier() {
        Koject.start()

        val value1 = inject<String>(MultipleMemberQualifier("id1", String::class))
        assertEquals("MultipleMemberQualifier-id1-string", value1)

        val value2 = inject<String>(MultipleMemberQualifier(kClass = Int::class))
        assertEquals("MultipleMemberQualifier-default-int", value2)
    }

    @Test
    fun successInject_qualifierWithMembersHolder() {
        Koject.start()

        val holder = inject<QualifierWithMembersHolder>()

        assertEquals("StringQualifier-id1", holder.stringQualifier1)
        assertEquals("StringQualifier-id2", holder.stringQualifier2)
        assertEquals("StringQualifier-default", holder.stringQualifierDefault)
        assertEquals("EnumQualifier-id1", holder.enumQualifier1)
        assertEquals("EnumQualifier-id2", holder.enumQualifier2)
        assertEquals("ClassQualifier-string", holder.classQualifier1)
        assertEquals("ClassQualifier-int", holder.classQualifier2)
        assertEquals("MultipleMemberQualifier-id1-string", holder.multipleMemberQualifier1)
        assertEquals("MultipleMemberQualifier-default-int", holder.multipleMemberQualifier2)
    }
}
