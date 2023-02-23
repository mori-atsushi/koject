package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.start
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertFails
import kotlin.test.assertIs

class InjectTest {
    @AfterTest
    fun clear() {
        Koject.stop()
    }

    @Test
    fun successInject() {
        Koject.start()

        val appClass1 = inject<AppClass1>()
        assertIs<AppClass1>(appClass1)

        val appClass2 = inject<AppClass2>()
        assertIs<AppClass2>(appClass2)

        val appClass3 = inject<AppClass3>()
        assertIs<AppClass3>(appClass3)
    }

    @Test
    fun failInject_notProvided() {
        Koject.start()

        assertFails {
            inject<NotProvided>()
        }
    }

    @Test
    fun failInject_notStarted() {
        assertFails {
            inject<AppClass1>()
        }
    }
}
