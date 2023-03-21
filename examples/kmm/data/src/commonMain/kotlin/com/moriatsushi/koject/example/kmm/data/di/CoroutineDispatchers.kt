package com.moriatsushi.koject.example.kmm.data.di

import com.moriatsushi.koject.Qualifier

@Qualifier
annotation class Dispatcher(val dispatcher: Dispatchers)

enum class Dispatchers {
    Main,
    IO,
}
