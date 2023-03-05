package com.moriatsushi.koject.internal

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.ExtrasBuilder

/**
 * Extra dependencies
 */
@InternalKojectApi
@ExperimentalKojectApi
class Extras(
    private val map: Map<Identifier, () -> Any>,
) {
    /**
     * Resolve dependencies and return instances from within extras
     *
     * @param id Target type Identifier
     */
    fun <T> resolve(id: Identifier): T {
        @Suppress("UNCHECKED_CAST")
        return (map[id] as (() -> T)).invoke()
    }

    companion object {
        internal fun of(builder: (ExtrasBuilder.() -> Unit)?): Extras {
            return if (builder != null) {
                ExtrasBuilder().apply { builder() }.build()
            } else {
                empty
            }
        }

        val empty = Extras(emptyMap())
    }
}
