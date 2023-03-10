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
        val holder = inject<WithQualifierExtrasHolder>()
        assertEquals("constructor", holder.constructorValue)
        assertEquals("property", holder.propertyValue)
        assertEquals("getter", holder.getterValue)
    }
}
