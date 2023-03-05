package com.moriatsushi.koject.example.kmm.di

import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Qualifier
import com.moriatsushi.koject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

expect object CoroutineDispatcherProvider {
    @MainDispatcher
    @Singleton
    @Provides
    fun provideMainDispatcher(): CoroutineDispatcher

    @IODispatcher
    @Singleton
    @Provides
    fun provideIODispatcher(): CoroutineDispatcher
}

@Qualifier
annotation class IODispatcher

@Qualifier
annotation class MainDispatcher
