package com.moriatsushi.koject.example.kmm.util

import java.util.UUID

internal actual fun randomUUID(): String {
    return UUID.randomUUID().toString()
}
