package com.moriatsushi.koject.internal

import com.moriatsushi.koject.Component

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
     * @param componentArguments Specify [Component.Arguments] to create [Component].
     */
    fun resolve(id: Identifier, componentArguments: Any?): Any?
}
