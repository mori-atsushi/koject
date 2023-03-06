package com.moriatsushi.koject.example.kmm.di

import android.content.Context
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.start

@OptIn(ExperimentalKojectApi::class)
fun startKoject(context: Context) {
    Koject.start {
        provides { context }
    }
}
