package com.moriatsushi.koject.internal

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Extras
import com.moriatsushi.koject.error.DynamicDependencyResolutionException

@ExperimentalKojectApi
internal class MergedExtras(
    private val extras1: Extras,
    private val extras2: Extras,
) : Extras {
    override fun <T> get(id: Identifier): T {
        return getOrNull<T>(id)
            ?: throw DynamicDependencyResolutionException(
                "$id is not provided dynamically",
            )
    }

    override fun <T> getOrNull(id: Identifier): T? {
        return extras2.getOrNull<T>(id)
            ?: extras1.getOrNull<T>(id)
    }
}
