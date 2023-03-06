package com.moriatsushi.koject.error

import com.moriatsushi.koject.Dynamic

/**
 * Failed to resolve [Dynamic] dependencies
 */
class DynamicDependencyResolutionException(message: String) :
    Exception(message)
