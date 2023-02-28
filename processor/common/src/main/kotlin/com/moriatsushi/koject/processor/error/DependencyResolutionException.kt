package com.moriatsushi.koject.processor.error

import com.google.devtools.ksp.symbol.KSNode

/**
 * Failed to resolve dependencies
 */
internal sealed class DependencyResolutionException(
    message: String,
    symbol: KSNode? = null,
) : CodeGenerationException(message, symbol)

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
    symbol: KSNode,
) : DependencyResolutionException(message, symbol)

/**
 * Injecting in wrong scope
 */
internal class WrongScopeException(message: String) :
    DependencyResolutionException(message)
