package com.moriatsushi.koject.processor.error

import com.moriatsushi.koject.component.Component
import com.moriatsushi.koject.component.ComponentExtras

/**
 * Failed to resolve dependencies
 */
internal sealed class DependencyResolutionException(message: String) :
    CodeGenerationException(message)

/**
 * Required dependencies not provided
 */
internal class NotProvidedException(message: String) :
    DependencyResolutionException(message)

/**
 * Same type is provided multiple times.
 */
internal class DuplicateProvidedException(
    message: String,
) : DependencyResolutionException(message)

/**
 * Multiple [ComponentExtras] are defined for the same [Component]
 */
internal class DuplicateComponentExtrasException(
    message: String,
) : DependencyResolutionException(message)

/**
 * Injecting in wrong scope
 */
internal class WrongScopeException(message: String) :
    DependencyResolutionException(message)
