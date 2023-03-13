package com.moriatsushi.koject.processor

import com.moriatsushi.koject.internal.InternalKojectApi

@InternalKojectApi
class DIProcessorOptions(
    val shouldGenerateContainer: Boolean = true,
    val measureDuration: Boolean = false,
)
