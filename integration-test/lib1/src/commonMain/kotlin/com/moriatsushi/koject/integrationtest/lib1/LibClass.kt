package com.moriatsushi.koject.integrationtest.lib1

import com.moriatsushi.koject.Provides

@Provides
class LibClass1

@Provides
class LibClass2(
    val libClass1: LibClass1,
)

@Provides
class LibClass3(
    val libClass1: LibClass1,
    val libClass2: LibClass2,
)
