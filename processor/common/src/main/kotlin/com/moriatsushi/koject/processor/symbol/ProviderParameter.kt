package com.moriatsushi.koject.processor.symbol

import com.moriatsushi.koject.internal.Location

internal data class ProviderParameter(
    val identifier: TypedIdentifier,
    val location: Location,
    val isRuntimeInject: Boolean,
)
