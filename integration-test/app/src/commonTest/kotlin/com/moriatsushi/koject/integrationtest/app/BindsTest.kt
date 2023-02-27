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
    fun failedInject_bindsBindsInterface1() {
        Koject.start()

        assertFailsWith<NotProvidedException> {
            inject<BindsInterface1>()
        }
    }

    @Test
    fun successInject_bindsBindsInterface2() {
        Koject.start()

        val value = inject<BindsInterface2>()
        assertIs<BindsMultipleImplement>(value)
    }

    @Test
    fun successInject_bindsNested() {
        Koject.start()

        val value = inject<BindChild1>()
        assertIs<BindsNestedImplement>(value)
    }

    @Test
    fun successInject_bindsAbstract() {
        Koject.start()

        val value = inject<BindsAbstract>()
        assertIs<BindsAbstractImplement>(value)
    }

    @Test
    fun successInject_bindsInterfaceHolder() {
        Koject.start()

        val holder = inject<BindsInterfaceHolder>()
        assertIs<BindsImplement>(holder.single)
        assertIs<BindsMultipleImplement>(holder.multiple)
        assertIs<BindsNestedImplement>(holder.nested)
        assertIs<BindsAbstract>(holder.abstract)
    }
}
