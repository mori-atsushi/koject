package com.moriatsushi.koject

import com.moriatsushi.koject.extras.KojectExtras

/**
 * Configurations to start [Koject]
 */
interface KojectBuilder {
    /**
     * Add extra dependencies.
     */
    @ExperimentalKojectApi
    fun addExtras(extras: KojectExtras)
}
