package com.moriatsushi.koject

import com.moriatsushi.koject.error.DynamicDependencyResolutionException
import com.moriatsushi.koject.internal.Identifier
import com.moriatsushi.koject.internal.MergedExtras

/**
 * Extra dependencies
 */
@ExperimentalKojectApi
interface Extras {
    /**
     * Return an instance of [id] from extras
     *
     * @throws DynamicDependencyResolutionException
     *  Throws if doesn't hold [id]
     */
    fun <T> get(id: Identifier): T

    /**
     * Return an instance of [id] from extras if hold
     */
    fun <T> getOrNull(id: Identifier): T?

    companion object {
        /**
         * None of extras
         */
        val empty = object : Extras {
            override fun <T> getOrNull(id: Identifier): T? {
                return null
            }

            override fun <T> get(id: Identifier): T {
                throw DynamicDependencyResolutionException(
                    "$id is not provided dynamically",
                )
            }
        }
    }
}

/**
 * Merge two [Extras]
 *
 * [other] takes precedence when duplicating.
 */
@ExperimentalKojectApi
infix fun Extras.merge(other: Extras): Extras {
    return MergedExtras(this, other)
}
