@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.start
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DynamicTest {
    @AfterTest
    fun clear() {
        Koject.stop()
    }

    @Test
    fun successInject_providesWhenInject_class() {
        Koject.start()

        val value = inject<DynamicClass> {
            provides { "runtime-string-class" }
        }
        assertEquals("runtime-string-class", value.id)
    }

    @Test
    fun successInject_providesWhenStart_class() {
        Koject.start {
            provides { "runtime-string-class" }
        }

        val value = inject<DynamicClass>()
        assertEquals("runtime-string-class", value.id)
    }

    @Test
    fun successInject_providesWhenInject_interface() {
        Koject.start()

        val value = inject<DynamicInterface> {
            provides { "runtime-string-interface" }
        }
        assertEquals("runtime-string-interface", value.id)
    }

    @Test
    fun successInject_providesWhenStart_interface() {
        Koject.start {
            provides { "runtime-string-interface" }
        }

        val value = inject<DynamicInterface>()
        assertEquals("runtime-string-interface", value.id)
    }

    @Test
    fun successInject_providesWhenInject_holder() {
        Koject.start()

        val holder = inject<DynamicHolder> {
            provides { "runtime-string" }
        }

        assertEquals("runtime-string", holder.value1.id)
        assertEquals("runtime-string", holder.value2.id)
    }

    @Test
    fun successInject_providesWhenStart_holder() {
        Koject.start {
            provides { "runtime-string" }
        }

        val holder = inject<DynamicHolder>()

        assertEquals("runtime-string", holder.value1.id)
        assertEquals("runtime-string", holder.value2.id)
    }
}
