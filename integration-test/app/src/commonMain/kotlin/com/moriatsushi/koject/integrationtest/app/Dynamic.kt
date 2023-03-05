@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Dynamic

@Provides
class DynamicClass(
    @Dynamic
    val id: String,
    val appClass: AppClass1,
)

@Provides
fun provideDynamicFunction(
    @Dynamic
    id: String,
    appClass: AppClass1,
): DynamicInterface {
    return object : DynamicInterface {
        override val id: String = id
        override val appClass: AppClass1 = appClass
    }
}

interface DynamicInterface {
    val id: String
    val appClass: AppClass1
}

@Provides
class DynamicHolder(
    val value1: DynamicClass,
    val value2: DynamicInterface,
)
