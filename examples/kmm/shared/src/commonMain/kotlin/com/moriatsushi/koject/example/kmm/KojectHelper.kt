package com.moriatsushi.koject.example.kmm

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.start

fun startKoject() {
    Koject.start()
}

fun injectGreeting(): Greeting {
    return inject()
}
