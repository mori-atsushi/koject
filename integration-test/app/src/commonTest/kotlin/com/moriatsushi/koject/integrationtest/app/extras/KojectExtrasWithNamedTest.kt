@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.app.extras

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.integrationtest.app.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class KojectExtrasWithNamedTest {
    @Test
    fun successInject() = Koject.runTest {
        val holder = inject<WithNamedComponentExtrasHolder>()
        assertEquals("named-constructor", holder.constructorValue)
        assertEquals("named-property", holder.propertyValue)
        assertEquals("named-getter", holder.getterValue)
    }
}
