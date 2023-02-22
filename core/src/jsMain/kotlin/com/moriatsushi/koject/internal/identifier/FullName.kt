package com.moriatsushi.koject.internal.identifier

import kotlin.reflect.KClass

/**
 * Get unique full name
 */
internal actual val KClass<*>.fullName: String
    get() = this.simpleName ?: error("not supported class:$this")
