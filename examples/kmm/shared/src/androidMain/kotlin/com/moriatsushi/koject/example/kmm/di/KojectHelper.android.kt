package com.moriatsushi.koject.example.kmm.di

import android.app.Application
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.android.application
import com.moriatsushi.koject.start

fun startKoject(application: Application) {
    Koject.start {
        application(application)
    }
}
