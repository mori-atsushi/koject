package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Binds
import com.moriatsushi.koject.Provides

@Binds
@Provides
class BindsImplement : BindsInterface

interface BindsInterface

@Binds(to = BindsInterface2::class)
@Provides
class BindsMultipleImplement : BindsInterface1, BindsInterface2

interface BindsInterface1

interface BindsInterface2

@Provides
class BindsInterfaceHolder(
    val single: BindsInterface,
    val multiple: BindsInterface2,
)
