package com.moriatsushi.koject.processor.error

/**
 * Failed to resolve dependencies
 */
sealed class DependencyResolutionException(message: String) :
    Exception(message)

/**
 *Required dependencies not provided
 */
class NotProvidedException(message: String) :
    DependencyResolutionException(message)

/**
 * Injecting in wrong scope
 */
class WrongScopeException(message: String) :
    DependencyResolutionException(message)
