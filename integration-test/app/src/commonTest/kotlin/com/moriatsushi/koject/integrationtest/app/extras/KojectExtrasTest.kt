@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.app.extras

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.error.MissingExtrasException
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.integrationtest.app.runTest
import com.moriatsushi.koject.start
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertIs
import kotlin.test.assertNotSame
import kotlin.test.assertSame

class KojectExtrasTest {
    @Test
    fun successInject() = Koject.runTest {
        val holder = inject<GlobalExtraHolder>()

        assertIs<GlobalExtraHolder>(holder)
    }

    @Test
    fun successInject_singleton() = Koject.runTest {
        val holder1 = inject<GlobalExtraHolder>()
        val holder2 = inject<GlobalExtraHolder>()

        assertNotSame(holder1, holder2)
        assertSame(holder1.constructorSingleton, holder2.constructorSingleton)
        assertSame(holder1.propertySingleton, holder2.propertySingleton)
        assertSame(holder1.getterSingleton, holder2.getterSingleton)
        assertNotSame(holder1.getterClass, holder2.getterClass)
    }

    @Test
    fun successInject_singletonHolder() = Koject.runTest {
        val holder1 = inject<SingletonGlobalExtraHolder>()
        val holder2 = inject<SingletonGlobalExtraHolder>()

        assertSame(holder1, holder2)
        assertSame(holder1.constructorSingleton, holder2.constructorSingleton)
        assertSame(holder1.propertySingleton, holder2.propertySingleton)
        assertSame(holder1.getterSingleton, holder2.getterSingleton)
    }

    @Test
    fun failStart_noExtras() {
        assertFailsWith<MissingExtrasException> {
            Koject.start()
        }
    }
}
