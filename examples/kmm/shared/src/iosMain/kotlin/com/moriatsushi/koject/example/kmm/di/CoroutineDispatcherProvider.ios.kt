package com.moriatsushi.koject.example.kmm.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newFixedThreadPoolContext

internal actual object CoroutineDispatcherProvider {
    actual fun provideMainDispatcher(): CoroutineDispatcher {
        return Dispatchers.Main.immediate
    }

    actual fun provideIODispatcher(): CoroutineDispatcher {
        // Dispatchers.IO not available on Kotlin/Native
        // https://github.com/Kotlin/kotlinx.coroutines/issues/3205
        return newFixedThreadPoolContext(nThreads = 200, name = "IO")
    }
}
