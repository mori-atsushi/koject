package com.moriatsushi.koject.integrationtest.lib1

import com.moriatsushi.koject.integrationtest.lib2.injectLib2ComponentClass
import com.moriatsushi.koject.integrationtest.lib3.injectLib3ComponentClass

fun injectNestedModuleComponents() {
    injectLib2ComponentClass()
    injectLib3ComponentClass()
}
