package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.start
import org.junit.After
import org.junit.Test
import kotlin.test.assertIs

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
