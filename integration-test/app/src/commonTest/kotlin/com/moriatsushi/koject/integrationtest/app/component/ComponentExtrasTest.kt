@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.app.component

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.error.NotProvidedException
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.start
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertIs

class ComponentExtrasTest {
    @AfterTest
    fun clear() {
        Koject.stop()
    }

    @Test
    fun successInject_withComponentExtras() {
        Koject.start()

        val extras = HasExtrasComponentExtras(
            ExtraClass(),
        )
        val value = inject<HasExtrasComponentClass>(
            componentExtras = extras,
        )

        assertIs<HasExtrasComponentClass>(value)
    }

    @Test
    fun failsInject_withoutComponentExtras() {
        Koject.start()

        assertFailsWith<NotProvidedException> {
            inject<HasExtrasComponentClass>()
        }
    }
}
