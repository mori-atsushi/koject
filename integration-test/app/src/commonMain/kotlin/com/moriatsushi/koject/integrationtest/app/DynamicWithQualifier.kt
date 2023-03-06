@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Dynamic
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DynamicID1

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DynamicID2

@Provides
class DynamicWithQualifierClass(
    @Dynamic
    @DynamicID1
    val id1: String,
    @Dynamic
    @DynamicID2
    val id2: String,
)

@Provides
fun provideDynamicWithQualifier(
    @Dynamic
    @DynamicID1
    id1: String,
    @Dynamic
    @DynamicID2
    id2: String,
): DynamicWithQualifierInterface {
    return object : DynamicWithQualifierInterface {
        override val id1: String = id1
        override val id2: String = id2
    }
}

interface DynamicWithQualifierInterface {
    val id1: String
    val id2: String
}

@Provides
class DynamicWithQualifierInterfaceHolder(
    val value1: DynamicWithQualifierClass,
    val value2: DynamicWithQualifierInterface,
)
