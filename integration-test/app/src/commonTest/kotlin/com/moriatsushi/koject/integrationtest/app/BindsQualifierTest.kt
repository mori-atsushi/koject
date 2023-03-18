package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import kotlin.test.Test
import kotlin.test.assertIs

class BindsQualifierTest {
    @Test
    fun successInject_bindsQualifierInterface() = Koject.runMain {
        val value1 = inject<BindsQualifierInterface>(BindsQualifier1())
        val value2 = inject<BindsQualifierInterface>(BindsQualifier2())
        assertIs<BindsQualifierImpl1>(value1)
        assertIs<BindsQualifierImpl2>(value2)
    }

    @Test
    fun successInject_bindsQualifierHolder() = Koject.runMain {
        val holder = inject<BindsQualifierHolder>()
        assertIs<BindsQualifierImpl1>(holder.value1)
        assertIs<BindsQualifierImpl2>(holder.value2)
    }
}
