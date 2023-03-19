package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.integrationtest.lib1.Lib2ClassHolder
import com.moriatsushi.koject.integrationtest.lib3.Lib3Class
import kotlin.test.Test

class NestedModuleTest {
    @Test
    fun successInject() = Koject.runMain {
        inject<Lib2ClassHolder>()
    }

    @Test
    fun successInject_multipleFactories() = Koject.runMain {
        inject<Lib3Class>()
    }
}
