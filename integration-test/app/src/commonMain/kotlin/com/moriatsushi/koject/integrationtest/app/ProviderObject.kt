package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Provides

object ProviderObject {
    @Provides
    fun provideInt(): Int {
        return 123
    }

    @Provides
    fun provideWithParameters(
        int: Int,
        type: ProvidableInterface,
        appClass: AppClass1,
    ): ProviderObjectWithParameters {
        return ProviderObjectWithParameters(
            int = int,
            type = type,
            appClass = appClass,
        )
    }
}

data class ProviderObjectWithParameters(
    val int: Int,
    val type: ProvidableInterface,
    val appClass: AppClass1,
)
