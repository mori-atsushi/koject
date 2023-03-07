package com.moriatsushi.koject.example.kmm.di

import android.annotation.SuppressLint
import android.content.Context
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Singleton

@SuppressLint("StaticFieldLeak")
internal object ContextProvider {
    lateinit var context: Context

    @Singleton
    @Provides
    fun provide(): Context {
        return context
    }
}
