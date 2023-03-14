package com.moriatsushi.koject.example.kmm.data.util

import java.util.UUID

internal actual fun randomUUID(): String {
    return UUID.randomUUID().toString()
}
