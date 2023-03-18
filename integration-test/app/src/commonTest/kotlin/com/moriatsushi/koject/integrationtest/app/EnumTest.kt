package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import kotlin.test.Test
import kotlin.test.assertEquals

class EnumTest {
    @Test
    fun successInject() = Koject.runMain {
        val holder = inject<EnumHolder>()
        assertEquals(SampleEnum.Item1, holder.item)
    }
}
