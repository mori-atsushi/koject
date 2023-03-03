package com.moriatsushi.koject.example.kmm.di

import android.content.Context
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.start

fun startKoject(context: Context) {
    ContextProvider.context = context
    Koject.start()
}
