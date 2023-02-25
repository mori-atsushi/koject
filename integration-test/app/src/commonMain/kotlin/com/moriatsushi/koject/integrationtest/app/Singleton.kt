package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Singleton

@Singleton
@Provides
class SingletonClass

interface SingletonInterface

@Provides
@Singleton
fun provideSingletonInterface(): SingletonInterface {
    return object : SingletonInterface {}
}

@Provides
class SingletonHolderClass(
    val singletonClass: SingletonClass,
    val singletonInterface: SingletonInterface,
)

@Provides
class NotSingletonClass
