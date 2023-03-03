package com.moriatsushi.koject.example.kmm.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual object CoroutineDispatcherProvider {
    actual fun provideIODispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    actual fun provideMainDispatcher(): CoroutineDispatcher {
        return Dispatchers.Main.immediate
    }
}
