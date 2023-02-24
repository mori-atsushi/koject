package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.start
import kotlin.test.assertIs
import org.junit.After
import org.junit.Test

class JvmClassTest {
    @After
    fun clear() {
        Koject.stop()
    }

    @Test
    fun successInject() {
        Koject.start()

        val value = inject<JvmClass>()
        assertIs<JvmClass>(value)
    }
}
