package com.moriatsushi.koject

import com.moriatsushi.koject.identifier.Identifier

/**
 * DI container
 *
 * Resolve of dependencies.
 */
interface Container {
    /**
     * Resolve dependencies and return an instance
     */
    fun resolve(id: Identifier): Any
}
