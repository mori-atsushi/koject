package com.moriatsushi.koject.example.kmm.di

import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Qualifier
import kotlinx.coroutines.CoroutineDispatcher

expect object CoroutineDispatcherProvider {
    @MainDispatcher
    @Provides
    fun provideMainDispatcher(): CoroutineDispatcher

    @IODispatcher
    @Provides
    fun provideIODispatcher(): CoroutineDispatcher
}

@Qualifier
annotation class IODispatcher

@Qualifier
annotation class MainDispatcher
