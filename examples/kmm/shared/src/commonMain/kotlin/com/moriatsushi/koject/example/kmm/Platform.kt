package com.moriatsushi.koject.example.kmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform