package com.moriatsushi.koject

import com.moriatsushi.koject.identifier.Identifier

class FakeContainer : Container {
    override fun resolve(id: Identifier): Any {
        return when (id) {
            Identifier("Int") -> 100
            Identifier("String") -> "test"
            else -> error("not supported")
        }
    }
}
