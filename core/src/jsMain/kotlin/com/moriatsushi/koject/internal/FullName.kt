package com.moriatsushi.koject.internal

import kotlin.reflect.KClass

/**
 * Get unique full name
 */
internal actual val KClass<*>.fullName: String
    get() = this.js.name
