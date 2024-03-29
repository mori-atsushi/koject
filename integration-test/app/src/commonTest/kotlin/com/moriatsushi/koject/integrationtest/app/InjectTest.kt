package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.error.KojectNotStartedException
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.lazyInject
import kotlin.test.Test
import kotlin.test.assertFails
import kotlin.test.assertFailsWith
import kotlin.test.assertIs

class InjectTest {
    @Test
    fun successInject() = Koject.runMain {
        val appClass1 = inject<AppClass1>()
        assertIs<AppClass1>(appClass1)

        val appClass2 = inject<AppClass2>()
        assertIs<AppClass2>(appClass2)

        val appClass3 = inject<AppClass3>()
        assertIs<AppClass3>(appClass3)
    }

    @Test
    fun successLazyInject() = Koject.runMain {
        val appClass1 by lazyInject<AppClass1>()
        assertIs<AppClass1>(appClass1)

        val appClass2 by lazyInject<AppClass2>()
        assertIs<AppClass2>(appClass2)

        val appClass3 by lazyInject<AppClass3>()
        assertIs<AppClass3>(appClass3)
    }

    @Test
    fun failInject_notProvided() = Koject.runMain {
        assertFails {
            inject<NotProvided>()
        }
    }

    @Test
    fun failInject_notStarted() {
        assertFailsWith<KojectNotStartedException> {
            inject<AppClass1>()
        }
    }
}
