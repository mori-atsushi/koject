package com.moriatsushi.koject.internal

import com.moriatsushi.koject.internal.identifier.Identifier

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
