package com.moriatsushi.koject.integrationtest.lib1

import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.integrationtest.lib2.Lib2Class
import com.moriatsushi.koject.integrationtest.lib2.Lib3ClassHolder

@Provides
class Lib2ClassHolder(
    private val value: Lib2Class,
    private val holder: Lib3ClassHolder,
)
