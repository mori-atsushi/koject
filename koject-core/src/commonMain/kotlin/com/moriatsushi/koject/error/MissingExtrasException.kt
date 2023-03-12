package com.moriatsushi.koject.error

import com.moriatsushi.koject.extras.KojectExtras

/**
 * [KojectExtras] is not set correctly.
 */
class MissingExtrasException(message: String) : Exception(message)
