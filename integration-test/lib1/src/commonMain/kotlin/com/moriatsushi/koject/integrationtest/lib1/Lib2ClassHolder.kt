package com.moriatsushi.koject.integrationtest.lib1

import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.integrationtest.lib2.Lib2Class

@Provides
class Lib2ClassHolder(
    private val value: Lib2Class,
)
