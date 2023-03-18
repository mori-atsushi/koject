package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import kotlin.test.assertIs
import org.junit.Test

class ExpectedClassTest {
    @Test
    fun successInject_ExpectedClassProvideByCommon() = Koject.runMain {
        val value = inject<ExpectedClass>()
        assertIs<ExpectedClass>(value)
    }
}
