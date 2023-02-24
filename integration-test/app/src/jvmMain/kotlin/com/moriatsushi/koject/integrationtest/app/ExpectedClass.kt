package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Provides

@Provides
actual class ExpectedClass(
    val commonClass: AppClass1,
    val jvmClass: JvmClass,
)
