@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.app.component

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.start
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ComponentExtrasWithQualifierTest {
    @AfterTest
    fun clear() {
        Koject.stop()
    }

    @Test
    fun successInject() {
        Koject.start()

        val extras = WithQualifierComponentExtras(
            "component-id1",
            "component-id2",
        )
        val value = inject<WithQualifierComponentClass>(
            componentExtras = extras,
        )

        assertEquals("component-id1", value.id1)
        assertEquals("component-id2", value.id2)
    }
}
