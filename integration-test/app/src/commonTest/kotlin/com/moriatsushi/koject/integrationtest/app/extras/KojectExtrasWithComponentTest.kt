@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.app.extras

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.integrationtest.app.runMain
import kotlin.test.Test

class KojectExtrasWithComponentTest {
    @Test
    fun successInject() = Koject.runMain {
        val extras = KojectExtrasComponentExtras(
            ComponentExtra(),
        )
        inject<KojectExtrasComponentClass>(
            componentExtras = extras,
        )
    }
}
