package com.moriatsushi.koject.integrationtest.app.test

import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.test.TestProvides

@Provides
class ProvideInTest

@TestProvides
class TestProvideInTest

@TestProvides
fun overrideTestInterface(): TestInterface {
    return object : TestInterface {
        override val name: String = "replaced"
    }
}
