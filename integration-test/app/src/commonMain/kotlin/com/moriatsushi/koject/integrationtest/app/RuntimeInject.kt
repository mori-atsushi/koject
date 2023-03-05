@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.RuntimeInject

@Provides
class RuntimeInjectClass(
    @RuntimeInject
    val id: String,
    val appClass: AppClass1,
)

@Provides
fun provideRuntimeInjectFunction(
    @RuntimeInject
    id: String,
    appClass: AppClass1,
): RuntimeInjectInterface {
    return object : RuntimeInjectInterface {
        override val id: String = id
        override val appClass: AppClass1 = appClass
    }
}

interface RuntimeInjectInterface {
    val id: String
    val appClass: AppClass1
}

@Provides
class RuntimeInjectHolder(
    val value1: RuntimeInjectClass,
    val value2: RuntimeInjectInterface,
)
