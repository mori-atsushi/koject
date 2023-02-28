package com.moriatsushi.koject.processor.symbol

import com.moriatsushi.koject.internal.Location
import com.moriatsushi.koject.internal.identifier.StringIdentifier

internal data class FactoryParameter(
    val identifier: StringIdentifier,
    val location: Location,
)
