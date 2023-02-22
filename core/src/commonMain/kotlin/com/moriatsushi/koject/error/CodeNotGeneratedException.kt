package com.moriatsushi.koject.error

/**
 * A error occurs when code generation fails or is not set up correctly.
 */
class CodeNotGeneratedException(message: String) : Exception(message)
