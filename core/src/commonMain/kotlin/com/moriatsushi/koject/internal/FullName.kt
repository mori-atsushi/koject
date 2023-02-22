package com.moriatsushi.koject.internal

import kotlin.reflect.KClass

/**
 * Get unique full name
 */
internal expect val KClass<*>.fullName: String
