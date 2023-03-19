package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.moriatsushi.koject.internal.Copied
import com.moriatsushi.koject.processor.analytics.findAnnotation
import com.moriatsushi.koject.processor.analytics.findArgumentByName

internal fun KSClassDeclaration.findCopiedCount(): Int {
    return findAnnotation<Copied>()
        ?.findArgumentByName<Int>("count")
        ?: 0
}
