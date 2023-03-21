package com.moriatsushi.koject.example.kmm.di.test

import com.moriatsushi.koject.example.kmm.data.di.Dispatcher
import com.moriatsushi.koject.example.kmm.data.di.Dispatchers.IO
import com.moriatsushi.koject.example.kmm.data.di.Dispatchers.Main
import com.moriatsushi.koject.test.TestProvides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@OptIn(ExperimentalCoroutinesApi::class)
internal object TestCoroutineDispatcherProvider {
    @Dispatcher(Main)
    @TestProvides
    fun provideMainDispatcher(): CoroutineDispatcher {
        return UnconfinedTestDispatcher()
    }

    @Dispatcher(IO)
    @TestProvides
    fun provideIODispatcher(): CoroutineDispatcher {
        return UnconfinedTestDispatcher()
    }
}
