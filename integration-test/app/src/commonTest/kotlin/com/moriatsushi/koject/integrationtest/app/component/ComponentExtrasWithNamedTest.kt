@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.app.component

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.start
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ComponentExtrasWithNamedTest {
    @AfterTest
    fun clear() {
        Koject.stop()
    }

    @Test
    fun successInject() {
        Koject.start()

        val extras = WithNamedComponentExtras()
        val value = inject<WithNamedComponentClass>(
            componentExtras = extras,
        )
        assertEquals("named-constructor", value.constructorValue)
        assertEquals("named-property", value.propertyValue)
        assertEquals("named-getter", value.getterValue)
    }
}
