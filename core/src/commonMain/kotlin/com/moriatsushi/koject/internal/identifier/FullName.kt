package com.moriatsushi.koject.internal.identifier

import kotlin.reflect.KClass

/**
 * Get unique full name
 */
internal expect val KClass<*>.fullName: String
