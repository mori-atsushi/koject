package com.moriatsushi.koject.internal

import com.moriatsushi.koject.ExperimentalKojectApi

/**
 * Koject DI container
 *
 * Resolve of dependencies.
 */
@InternalKojectApi
interface Container {
    /**
     * Resolve dependencies and return an instance
     *
     * @param id Target type Identifier
     * @param extras Extra dependencies
     */
    @ExperimentalKojectApi
    fun resolve(id: Identifier, extras: Extras): Any?
}
