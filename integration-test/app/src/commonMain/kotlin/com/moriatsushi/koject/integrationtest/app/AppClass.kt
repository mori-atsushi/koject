package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Provides

@Provides
class AppClass1

@Provides
class AppClass2(
    val appClass1: AppClass1,
)

@Provides
class AppClass3(
    val appClass1: AppClass1,
    val appClass2: AppClass2,
)
