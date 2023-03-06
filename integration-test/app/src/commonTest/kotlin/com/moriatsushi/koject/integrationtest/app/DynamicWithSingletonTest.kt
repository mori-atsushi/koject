@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.error.DynamicDependencyResolutionException
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.start
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class DynamicWithSingletonTest {
    @AfterTest
    fun clear() {
        Koject.stop()
    }

    @Test
    fun successInject_providesWhenStart_class() {
        Koject.start {
            provides { "runtime-string-class" }
        }

        val value = inject<DynamicWithSingletonClass>()
        assertEquals("runtime-string-class", value.id)
    }

    @Test
    fun failedInject_providesWhenInject_class() {
        Koject.start()

        assertFailsWith<DynamicDependencyResolutionException> {
            inject<DynamicWithSingletonClass> {
                provides { "runtime-string-class" }
            }
        }
    }

    @Test
    fun successInject_providesWhenStart_interface() {
        Koject.start {
            provides { "runtime-string-interface" }
        }

        val value = inject<DynamicWithSingletonInterface>()
        assertEquals("runtime-string-interface", value.id)
    }

    @Test
    fun failedInject_providesWhenInject_interface() {
        Koject.start()

        assertFailsWith<DynamicDependencyResolutionException> {
            inject<DynamicWithSingletonInterface> {
                provides { "runtime-string-interface" }
            }
        }
    }

    @Test
    fun successInject_providesWhenStart_holder() {
        Koject.start {
            provides { "runtime-string" }
        }

        val holder = inject<DynamicWithSingletonHolder>()

        assertEquals("runtime-string", holder.value1.id)
        assertEquals("runtime-string", holder.value2.id)
    }

    @Test
    fun failedInject_providesWhenInject_holder() {
        Koject.start()

        assertFailsWith<DynamicDependencyResolutionException> {
            inject<DynamicWithSingletonHolder> {
                provides { "runtime-string" }
            }
        }
    }
}
