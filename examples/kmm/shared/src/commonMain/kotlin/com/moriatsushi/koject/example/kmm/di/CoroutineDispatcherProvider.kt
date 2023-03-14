package com.moriatsushi.koject.example.kmm.di

import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Singleton
import com.moriatsushi.koject.example.kmm.data.di.IODispatcher
import com.moriatsushi.koject.example.kmm.data.di.MainDispatcher
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
