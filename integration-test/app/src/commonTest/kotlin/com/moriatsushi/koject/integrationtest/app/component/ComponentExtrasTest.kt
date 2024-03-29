@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.app.component

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.error.NotProvidedException
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.integrationtest.app.runMain
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertIs

class ComponentExtrasTest {
    @Test
    fun successInject_withComponentExtras() = Koject.runMain {
        val extras = HasExtrasComponentExtras(
            ExtraClass1(),
        )
        val value = inject<HasExtrasComponentClass>(
            componentExtras = extras,
        )

        assertIs<HasExtrasComponentClass>(value)
    }

    @Test
    fun failsInject_withoutComponentExtras() = Koject.runMain {
        assertFailsWith<NotProvidedException> {
            inject<HasExtrasComponentClass>()
        }
    }
}
