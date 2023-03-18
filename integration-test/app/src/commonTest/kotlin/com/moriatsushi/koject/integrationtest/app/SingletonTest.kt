package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.lazyInject
import kotlin.test.Test
import kotlin.test.assertNotSame
import kotlin.test.assertSame

class SingletonTest {
    @Test
    fun sameInstance_class() = Koject.runMain {
        val value1 = inject<SingletonClass>()
        val value2 = inject<SingletonClass>()
        assertSame(value1, value2)
    }

    @Test
    fun sameInstance_interface() = Koject.runMain {
        val value1 = inject<SingletonInterface>()
        val value2 = inject<SingletonInterface>()
        assertSame(value1, value2)
    }

    @Test
    fun sameInstance_singletonHolder() = Koject.runMain {
        val value1 = inject<SingletonHolderClass>()
        val value2 = inject<SingletonHolderClass>()

        assertNotSame(value1, value2)
        assertSame(value1.singletonClass, value2.singletonClass)
        assertSame(value1.singletonInterface, value2.singletonInterface)
    }

    @Test
    fun sameInstance_lazyInject() = Koject.runMain {
        val value1 by lazyInject<SingletonClass>()
        val value2 by lazyInject<SingletonClass>()
        assertSame(value1, value2)
    }

    @Test
    fun notSameInstance_default() = Koject.runMain {
        val value1 = inject<NotSingletonClass>()
        val value2 = inject<NotSingletonClass>()
        assertNotSame(value1, value2)
    }
}
