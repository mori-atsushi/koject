@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Dynamic
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Singleton

@Singleton
@Provides
class DynamicWithSingletonClass(
    @Dynamic
    val id: String,
    val singleton: SingletonClass,
)

@Singleton
@Provides
fun provideDynamicWithSingletonInterface(
    @Dynamic
    id: String,
    singleton: SingletonClass,
): DynamicWithSingletonInterface {
    return object : DynamicWithSingletonInterface {
        override val id: String = id
        override val singleton: SingletonClass = singleton
    }
}

interface DynamicWithSingletonInterface {
    val id: String
    val singleton: SingletonClass
}

@Singleton
@Provides
class DynamicWithSingletonHolder(
    val value1: DynamicWithSingletonClass,
    val value2: DynamicWithSingletonInterface,
)
