package com.moriatsushi.koject

import com.moriatsushi.koject.internal.Container
import com.moriatsushi.koject.internal.identifier.Identifier

class FakeContainer : Container {
    override fun resolve(id: Identifier): Any? {
        return when (id) {
            Identifier.of<Int>() -> 100
            Identifier.of<String>() -> "test"
            else -> null
        }
    }
}
