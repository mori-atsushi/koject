package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import kotlin.test.Test
import kotlin.test.assertIs

class ObjectTest {
    @Test
    fun successInject() = Koject.runMain {
        val value1 = inject<ProvidableObject>()
        assertIs<ProvidableObject>(value1)

        val value2 = inject<IProvidableObject>()
        assertIs<IProvidableObject>(value2)

        val value3 = inject<ProvidableObjectHolder.NestedObject>()
        assertIs<ProvidableObjectHolder.NestedObject>(value3)

        val value4 = inject<ProvidableObjectHolder.Companion>()
        assertIs<ProvidableObjectHolder.Companion>(value4)
    }

    @Test
    fun successInject_holder() = Koject.runMain {
        val holder = inject<ProvidableObjectHolder>()
        assertIs<ProvidableObjectHolder>(holder)
    }
}
