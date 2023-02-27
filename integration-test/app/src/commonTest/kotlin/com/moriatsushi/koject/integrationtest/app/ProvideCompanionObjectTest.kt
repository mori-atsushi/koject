package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.start
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertIs

class ProvideCompanionObjectTest {
    @AfterTest
    fun clear() {
        Koject.stop()
    }

    @Test
    fun successInject_provideCompanionObject() {
        Koject.start()

        val actual = inject<ProvideCompanionObject>()
        assertIs<ProvideCompanionObject>(actual)
    }

    @Test
    fun successInject_provideCompanionObjectHolder() {
        Koject.start()

        val actual = inject<ProvideCompanionObjectHolder>()
        assertIs<ProvideCompanionObject>(actual.value)
    }
}
