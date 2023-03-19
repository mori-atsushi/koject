package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.moriatsushi.koject.internal.Copied
import com.moriatsushi.koject.processor.analytics.findAnnotation
import com.moriatsushi.koject.processor.analytics.findArgumentByName
import com.moriatsushi.koject.processor.code.escapedForCode
import com.squareup.kotlinpoet.ClassName

internal fun KSClassDeclaration.findCopiedCount(): Int {
    return findAnnotation<Copied>()
        ?.findArgumentByName<Int>("count")
        ?: 0
}

internal fun copiedName(base: ClassName, moduleName: String): ClassName {
    val simpleName = "_${moduleName.escapedForCode}_${base.simpleName}"
    return ClassName(base.packageName, simpleName)
}
