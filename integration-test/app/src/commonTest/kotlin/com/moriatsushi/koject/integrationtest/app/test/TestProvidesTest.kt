package com.moriatsushi.koject.integrationtest.app.test

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.integrationtest.app.runTest
import kotlin.test.Test

class TestProvidesTest {
    @Test
    fun successInject() = Koject.runTest {
        inject<ProvideInTest>()
    }
}
