package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import kotlin.test.assertIs
import org.junit.Test

class JvmClassTest {
    @Test
    fun successInject() = Koject.runTest {
        val value = inject<JvmClass>()
        assertIs<JvmClass>(value)
    }
}
