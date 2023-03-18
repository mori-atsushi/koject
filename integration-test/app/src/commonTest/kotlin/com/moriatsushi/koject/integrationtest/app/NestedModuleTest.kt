package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.integrationtest.lib1.Lib2ClassHolder
import kotlin.test.Test

class NestedModuleTest {
    @Test
    fun successInject() = Koject.runMain {
        inject<Lib2ClassHolder>()
    }
}
