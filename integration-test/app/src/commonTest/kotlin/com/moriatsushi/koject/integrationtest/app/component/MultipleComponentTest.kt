@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.app.component

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.integrationtest.app.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class MultipleComponentTest {
    @Test
    fun successInject_classes() = Koject.runTest {
        val type1 = inject<MultipleCustomComponentClass>(
            componentExtras = CustomComponent1Extras(),
        )
        val type2 = inject<MultipleCustomComponentClass>(
            componentExtras = CustomComponent2Extras(),
        )

        assertEquals("custom-component-1", type1.value)
        assertEquals("custom-component-2", type2.value)
    }

    @Test
    fun successInject_holders() = Koject.runTest {
        val holder1 = inject<CustomComponent1Holder>(
            componentExtras = CustomComponent1Extras(),
        )
        val holder2 = inject<CustomComponent2Holder>(
            componentExtras = CustomComponent2Extras(),
        )

        assertEquals("custom-component-1", holder1.value.value)
        assertEquals("custom-component-2", holder2.value.value)
    }
}
