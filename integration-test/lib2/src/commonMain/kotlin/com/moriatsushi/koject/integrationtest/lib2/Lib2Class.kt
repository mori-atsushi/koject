package com.moriatsushi.koject.integrationtest.lib2

import com.moriatsushi.koject.Provides

@Provides
class Lib2Class

@Provides
class Lib3ClassHolder(
    private val value: Lib2Class,
)
