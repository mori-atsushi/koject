package com.moriatsushi.koject

import com.moriatsushi.koject.component.ComponentExtras
import com.moriatsushi.koject.internal.Container
import com.moriatsushi.koject.internal.Identifier

class FakeContainer : Container {
    @OptIn(ExperimentalKojectApi::class)
    override fun resolve(id: Identifier, componentExtras: ComponentExtras<*>?): Any? {
        return when (id) {
            Identifier.of<Int>() -> 100
            Identifier.of<String>() -> "test"
            else -> null
        }
    }
}
