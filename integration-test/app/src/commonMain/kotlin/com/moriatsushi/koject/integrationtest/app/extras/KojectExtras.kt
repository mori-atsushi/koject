@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.app.extras

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Singleton
import com.moriatsushi.koject.extras.KojectExtras

class GlobalExtras(
    val constructorClass: GlobalExtraClass1,
    @Singleton
    val constructorSingleton: GlobalExtraSingleton1,
) : KojectExtras {
    val propertyClass: GlobalExtraClass2 = GlobalExtraClass2()

    @Singleton
    val propertySingleton: GlobalExtraSingleton2 = GlobalExtraSingleton2()

    val getterClass: GlobalExtraClass3
        get() = GlobalExtraClass3()

    @Singleton
    val getterSingleton: GlobalExtraSingleton3
        get() = GlobalExtraSingleton3()
}

class GlobalExtraClass1
class GlobalExtraClass2
class GlobalExtraClass3
class GlobalExtraSingleton1
class GlobalExtraSingleton2
class GlobalExtraSingleton3

@Provides
class GlobalExtraHolder(
    val constructorClass: GlobalExtraClass1,
    val constructorSingleton: GlobalExtraSingleton1,
    val propertyClass: GlobalExtraClass2,
    val propertySingleton: GlobalExtraSingleton2,
    val getterClass: GlobalExtraClass3,
    val getterSingleton: GlobalExtraSingleton3,
)

@Singleton
@Provides
class SingletonGlobalExtraHolder(
    val constructorSingleton: GlobalExtraSingleton1,
    val propertySingleton: GlobalExtraSingleton2,
    val getterSingleton: GlobalExtraSingleton3,
)
