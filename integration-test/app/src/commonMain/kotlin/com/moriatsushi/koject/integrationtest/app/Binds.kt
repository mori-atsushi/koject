package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Binds
import com.moriatsushi.koject.Provides

@Binds
@Provides
class BindsImplement : BindsInterface

interface BindsInterface

@Provides
class BindsInterfaceHolder(
    val value: BindsInterface,
)
