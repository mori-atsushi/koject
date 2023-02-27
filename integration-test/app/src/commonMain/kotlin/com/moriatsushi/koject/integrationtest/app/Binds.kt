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

@Binds(to = BindChild1::class)
@Provides
class BindsNestedImplement : BindChild2

interface BindChild2 : BindChild1

interface BindChild1

@Binds
@Provides
class BindsAbstractImplement : BindsAbstract()

abstract class BindsAbstract

@Provides
class BindsInterfaceHolder(
    val single: BindsInterface,
    val multiple: BindsInterface2,
    val nested: BindChild1,
    val abstract: BindsAbstract,
)
