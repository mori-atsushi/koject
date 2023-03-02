package com.moriatsushi.koject.example.kmm

import android.app.Application

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoject()
    }
}
