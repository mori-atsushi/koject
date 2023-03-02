package com.moriatsushi.koject.example.kmm.util

import platform.Foundation.NSUUID

internal actual fun randomUUID(): String {
    return NSUUID().UUIDString()
}
