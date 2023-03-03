package com.moriatsushi.koject.example.kmm

import android.app.Application
import com.moriatsushi.koject.example.kmm.di.startKoject

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoject(this)
    }
}
