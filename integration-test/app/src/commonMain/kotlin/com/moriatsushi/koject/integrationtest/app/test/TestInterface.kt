package com.moriatsushi.koject.integrationtest.app.test

import com.moriatsushi.koject.Provides

interface TestInterface {
    val name: String
}

@Provides
fun provideTestInterface(): TestInterface {
    return object : TestInterface {
        override val name: String = "base"
    }
}

@Provides
class TestInterfaceHolder(
    val value: TestInterface,
)
