package com.moriatsushi.koject.example.kmm.di

import android.annotation.SuppressLint
import android.content.Context
import com.moriatsushi.koject.Provides

@SuppressLint("StaticFieldLeak")
internal object ContextProvider {
    lateinit var context: Context

    @Provides
    fun provideContext(): Context {
        return context
    }
}
