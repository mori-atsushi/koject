package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.start
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertNotSame
import kotlin.test.assertSame

class SingletonTest {
    @AfterTest
    fun clear() {
        Koject.stop()
    }

    @Test
    fun sameInstance_class() {
        Koject.start()

        val value1 = inject<SingletonClass>()
        val value2 = inject<SingletonClass>()
        assertSame(value1, value2)
    }

    @Test
    fun sameInstance_interface() {
        Koject.start()

        val value1 = inject<SingletonInterface>()
        val value2 = inject<SingletonInterface>()
        assertSame(value1, value2)
    }

    @Test
    fun sameInstance_singletonHolder() {
        Koject.start()

        val value1 = inject<SingletonHolderClass>()
        val value2 = inject<SingletonHolderClass>()

        assertNotSame(value1, value2)
        assertSame(value1.singletonClass, value2.singletonClass)
        assertSame(value1.singletonInterface, value2.singletonInterface)
    }

    @Test
    fun notSameInstance_default() {
        Koject.start()

        val value1 = inject<NotSingletonClass>()
        val value2 = inject<NotSingletonClass>()
        assertNotSame(value1, value2)
    }
}
