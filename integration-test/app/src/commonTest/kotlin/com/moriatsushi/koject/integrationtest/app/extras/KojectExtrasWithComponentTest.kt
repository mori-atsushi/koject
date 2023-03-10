@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.app.extras

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.integrationtest.app.runTest
import kotlin.test.Test

class KojectExtrasWithComponentTest {
    @Test
    fun successInject() = Koject.runTest {
        val extras = KojectExtrasComponentExtras(
            ComponentExtra(),
        )
        inject<KojectExtrasComponentClass>(
            componentExtras = extras,
        )
    }
}
