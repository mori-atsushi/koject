package com.moriatsushi.koject.internal

import com.moriatsushi.koject.internal.identifier.Identifier

/**
 * DI container
 *
 * Resolve of dependencies.
 */
@InternalKojectApi
interface Container {
    /**
     * Resolve dependencies and return an instance
     */
    fun resolve(id: Identifier): Any?
}
