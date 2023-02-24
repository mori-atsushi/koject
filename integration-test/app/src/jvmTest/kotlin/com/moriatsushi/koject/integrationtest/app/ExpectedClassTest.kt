package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.start
import org.junit.After
import org.junit.Test
import kotlin.test.assertIs

class ExpectedClassTest {
    @After
    fun clear() {
        Koject.stop()
    }

    @Test
    fun successInject_ExpectedClassProvideByCommon() {
        Koject.start()

        val value = inject<ExpectedClass>()
        assertIs<ExpectedClass>(value)
    }
}
