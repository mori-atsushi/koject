package com.moriatsushi.koject.example.kmm

import android.app.Application
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.android.application
import com.moriatsushi.koject.test.startTest

class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Koject.startTest {
            application(this@TestApplication)
        }
    }
}
