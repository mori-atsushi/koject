package com.moriatsushi.koject.processor.error

/**
 * Failed to resolve dependencies
 */
internal sealed class DependencyResolutionException(message: String) :
    CodeGenerationException(message)

/**
 *Required dependencies not provided
 */
internal class NotProvidedException(message: String) :
    DependencyResolutionException(message)

/**
 * Injecting in wrong scope
 */
internal class WrongScopeException(message: String) :
    DependencyResolutionException(message)
