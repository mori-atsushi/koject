package com.moriatsushi.koject.example.kmm

import android.app.Application
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.android.application
import com.moriatsushi.koject.start

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Koject.start {
            application(this@MyApplication)
        }
    }
}
