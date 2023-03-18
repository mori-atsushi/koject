@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.app.component

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.error.NotProvidedException
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.integrationtest.app.AppClass1
import com.moriatsushi.koject.integrationtest.app.runMain
import com.moriatsushi.koject.lazyInject
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertIs

class ComponentTest {
    @Test
    fun successInject_withComponentExtras() = Koject.runMain {
        val value = inject<CustomComponentClass>(
            componentExtras = CustomComponentExtras(),
        )

        assertIs<CustomComponentClass>(value)
    }

    @Test
    fun successLazyInject_withComponentExtras() = Koject.runMain {
        val value by lazyInject<CustomComponentClass>(
            componentExtrasProducer = {
                CustomComponentExtras()
            },
        )

        assertIs<CustomComponentClass>(value)
    }

    @Test
    fun successInject_inRootComponent() = Koject.runMain {
        val value = inject<AppClass1>(
            componentExtras = CustomComponentExtras(),
        )

        assertIs<AppClass1>(value)
    }

    @Test
    fun failsInject_withoutComponentExtras() = Koject.runMain {
        assertFailsWith<NotProvidedException> {
            inject<CustomComponentClass>()
        }
    }
}
