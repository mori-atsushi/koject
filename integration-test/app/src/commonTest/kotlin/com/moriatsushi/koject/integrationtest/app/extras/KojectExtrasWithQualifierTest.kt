@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.app.extras

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.integrationtest.app.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class KojectExtrasWithQualifierTest {
    @Test
    fun successInject() = Koject.runTest {
        val value = inject<WithQualifierExtrasHolder>()
        assertEquals("constructor", value.constructorValue)
        assertEquals("property", value.propertyValue)
        assertEquals("getter", value.getterValue)
    }
}
