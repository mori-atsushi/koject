@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.app.component

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.integrationtest.app.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ComponentExtrasWithQualifierTest {
    @Test
    fun successInject() = Koject.runTest {
        val extras = WithQualifierComponentExtras()
        val value = inject<WithQualifierComponentClass>(
            componentExtras = extras,
        )
        assertEquals("component-constructor", value.constructorValue)
        assertEquals("component-property", value.propertyValue)
        assertEquals("component-getter", value.getterValue)
    }
}
