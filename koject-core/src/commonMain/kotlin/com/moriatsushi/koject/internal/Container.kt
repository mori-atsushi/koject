package com.moriatsushi.koject.internal

import com.moriatsushi.koject.component.Component
import com.moriatsushi.koject.component.ComponentExtras

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
     * @param componentExtras Specify [ComponentExtras] to create [Component].
     */
    fun resolve(id: Identifier, componentExtras: Any?): Any?
}
