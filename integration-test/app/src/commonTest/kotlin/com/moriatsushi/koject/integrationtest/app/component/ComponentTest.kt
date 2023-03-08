@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.app.component

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.error.NotProvidedException
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.integrationtest.app.AppClass1
import com.moriatsushi.koject.start
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertIs

class ComponentTest {
    @AfterTest
    fun clear() {
        Koject.stop()
    }

    @Test
    fun successInject_withComponentExtras() {
        Koject.start()

        val value = inject<CustomComponentClass>(
            componentExtras = CustomComponentExtras(),
        )

        assertIs<CustomComponentClass>(value)
    }

    @Test
    fun successInject_inRootComponent() {
        Koject.start()

        val value = inject<AppClass1>(
            componentExtras = CustomComponentExtras(),
        )

        assertIs<AppClass1>(value)
    }

    @Test
    fun failsInject_withoutComponentExtras() {
        Koject.start()

        assertFailsWith<NotProvidedException> {
            inject<CustomComponentClass>()
        }
    }
}
