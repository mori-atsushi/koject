package com.moriatsushi.koject.example.kmm.di

import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.example.kmm.data.di.Dispatcher
import com.moriatsushi.koject.example.kmm.data.di.Dispatchers.IO
import com.moriatsushi.koject.example.kmm.data.di.Dispatchers.Main
import kotlinx.coroutines.CoroutineDispatcher

internal expect object CoroutineDispatcherProvider {
    @Dispatcher(Main)
    @Provides
    fun provideMainDispatcher(): CoroutineDispatcher

    @Dispatcher(IO)
    @Provides
    fun provideIODispatcher(): CoroutineDispatcher
}
