@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.start
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ComponentTest {
    @AfterTest
    fun clear() {
        Koject.stop()
    }

    @Test
    fun successInject_classes() {
        Koject.start()

        val type1 = inject<CustomComponentClass>(
            componentArguments = CustomComponent1Arguments(),
        )
        val type2 = inject<CustomComponentClass>(
            componentArguments = CustomComponent2Arguments(),
        )

        assertEquals("custom-component-1", type1.value)
        assertEquals("custom-component-2", type2.value)
    }

    @Test
    fun successInject_holders() {
        Koject.start()

        val holder1 = inject<CustomComponent1Holder>(
            componentArguments = CustomComponent1Arguments(),
        )
        val holder2 = inject<CustomComponent2Holder>(
            componentArguments = CustomComponent2Arguments(),
        )

        assertEquals("custom-component-1", holder1.value.value)
        assertEquals("custom-component-2", holder2.value.value)
    }
}
