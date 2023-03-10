package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.error.NotProvidedException
import com.moriatsushi.koject.inject
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertIs

class BindsTest {
    @Test
    fun successInject_bindsInterface() = Koject.runTest {
        val value = inject<BindsInterface>()
        assertIs<BindsImplement>(value)
    }

    @Test
    fun failInject_bindsImplement() = Koject.runTest {
        assertFailsWith<NotProvidedException> {
            inject<BindsImplement>()
        }
    }

    @Test
    fun failedInject_bindsBindsInterface1() = Koject.runTest {
        assertFailsWith<NotProvidedException> {
            inject<BindsInterface1>()
        }
    }

    @Test
    fun successInject_bindsBindsInterface2() = Koject.runTest {
        val value = inject<BindsInterface2>()
        assertIs<BindsMultipleImplement>(value)
    }

    @Test
    fun successInject_bindsNested() = Koject.runTest {
        val value = inject<BindChild1>()
        assertIs<BindsNestedImplement>(value)
    }

    @Test
    fun successInject_bindsAbstract() = Koject.runTest {
        val value = inject<BindsAbstract>()
        assertIs<BindsAbstractImplement>(value)
    }

    @Test
    fun successInject_bindsInterfaceHolder() = Koject.runTest {
        val holder = inject<BindsInterfaceHolder>()
        assertIs<BindsImplement>(holder.single)
        assertIs<BindsMultipleImplement>(holder.multiple)
        assertIs<BindsNestedImplement>(holder.nested)
        assertIs<BindsAbstract>(holder.abstract)
    }
}
