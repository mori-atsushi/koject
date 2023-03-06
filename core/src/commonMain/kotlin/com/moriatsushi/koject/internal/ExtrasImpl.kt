@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.internal

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Extras
import com.moriatsushi.koject.error.DynamicDependencyResolutionException

internal class ExtrasImpl(
    private val map: Map<Identifier, () -> Any>,
) : Extras {
    override fun <T> get(id: Identifier): T {
        return getOrNull<T>(id)
            ?: throw DynamicDependencyResolutionException(
                "$id is not provided dynamically",
            )
    }

    override fun <T> getOrNull(id: Identifier): T? {
        val factory = map[id] ?: return null
        @Suppress("UNCHECKED_CAST")
        return factory() as T
    }
}
