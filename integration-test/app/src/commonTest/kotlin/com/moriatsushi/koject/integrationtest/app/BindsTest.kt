package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.error.NotProvidedException
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.start
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertIs

class BindsTest {
    @AfterTest
    fun clear() {
        Koject.stop()
    }

    @Test
    fun successInject_bindsInterface() {
        Koject.start()

        val value = inject<BindsInterface>()
        assertIs<BindsImplement>(value)
    }

    @Test
    fun failInject_bindsImplement() {
        Koject.start()

        assertFailsWith<NotProvidedException> {
            inject<BindsImplement>()
        }
    }

    @Test
    fun successInject_bindsInterfaceHolder() {
        Koject.start()

        val holder = inject<BindsInterfaceHolder>()
        assertIs<BindsImplement>(holder.value)
    }
}
