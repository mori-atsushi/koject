package com.moriatsushi.koject.internal

/**
 * Koject DI container
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
