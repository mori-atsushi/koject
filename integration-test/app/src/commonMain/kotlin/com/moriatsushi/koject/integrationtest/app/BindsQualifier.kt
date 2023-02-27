package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Binds
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BindsQualifier1

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BindsQualifier2

@BindsQualifier1
@Binds
@Provides
class BindsQualifierImpl1 : BindsQualifierInterface

@BindsQualifier2
@Binds
@Provides
class BindsQualifierImpl2 : BindsQualifierInterface

interface BindsQualifierInterface

@Provides
class BindsQualifierHolder(
    @BindsQualifier1
    val value1: BindsQualifierInterface,
    @BindsQualifier2
    val value2: BindsQualifierInterface,
)
