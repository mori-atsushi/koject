package com.moriatsushi.koject.example.kmm

import com.moriatsushi.koject.Provides

@Provides
class Greeting(
    private val platform: Platform,
) {
    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}
