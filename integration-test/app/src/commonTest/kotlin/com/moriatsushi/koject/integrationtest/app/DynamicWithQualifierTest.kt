@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.start
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DynamicWithQualifierTest {
    @AfterTest
    fun clear() {
        Koject.stop()
    }

    @Test
    fun successInject_class() {
        Koject.start()

        val value = inject<DynamicWithQualifierClass> {
            provides(DynamicID1()) {
                "runtime-string-id1"
            }
            provides(DynamicID2()) {
                "runtime-string-id2"
            }
        }
        assertEquals("runtime-string-id1", value.id1)
        assertEquals("runtime-string-id2", value.id2)
    }

    @Test
    fun successInject_interface() {
        Koject.start()

        val value = inject<DynamicWithQualifierInterface> {
            provides(DynamicID1()) {
                "runtime-string-id1"
            }
            provides(DynamicID2()) {
                "runtime-string-id2"
            }
        }
        assertEquals("runtime-string-id1", value.id1)
        assertEquals("runtime-string-id2", value.id2)
    }

    @Test
    fun successInject_holder() {
        Koject.start()

        val holder = inject<DynamicWithQualifierInterfaceHolder> {
            provides(DynamicID1()) {
                "runtime-string-id1"
            }
            provides(DynamicID2()) {
                "runtime-string-id2"
            }
        }

        assertEquals("runtime-string-id1", holder.value1.id1)
        assertEquals("runtime-string-id2", holder.value1.id2)
        assertEquals("runtime-string-id1", holder.value2.id1)
        assertEquals("runtime-string-id2", holder.value2.id2)
    }
}
