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
     *
     * @param id Target type Identifier
     */
    fun resolve(id: Identifier): Any?
}
