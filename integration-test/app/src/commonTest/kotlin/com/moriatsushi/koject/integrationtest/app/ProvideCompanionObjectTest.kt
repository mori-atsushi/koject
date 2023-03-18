package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import kotlin.test.Test
import kotlin.test.assertIs

class ProvideCompanionObjectTest {
    @Test
    fun successInject_provideCompanionObject() = Koject.runMain {
        val actual = inject<ProvideCompanionObject>()
        assertIs<ProvideCompanionObject>(actual)
    }

    @Test
    fun successInject_provideCompanionObjectHolder() = Koject.runMain {
        val actual = inject<ProvideCompanionObjectHolder>()
        assertIs<ProvideCompanionObject>(actual.value)
    }
}
