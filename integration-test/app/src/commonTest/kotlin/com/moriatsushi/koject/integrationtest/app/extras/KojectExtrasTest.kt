@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.app.extras

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.start
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertIs

class KojectExtrasTest {
    @AfterTest
    fun clear() {
        Koject.stop()
    }

    @Test
    fun successInject() {
        val extras = GlobalExtras(GlobalExtraClass())
        Koject.addExtras(extras)
        Koject.start()

        val value = inject<GlobalExtraHolder>()

        assertIs<GlobalExtraHolder>(value)
    }
}
