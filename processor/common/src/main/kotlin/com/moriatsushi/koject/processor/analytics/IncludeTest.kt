package com.moriatsushi.koject.processor.analytics

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.processing.Resolver
import com.moriatsushi.koject.processor.code.Names

internal fun Resolver.includeTest(): Boolean {
    @OptIn(KspExperimental::class)
    return this.getDeclarationsFromPackage(Names.testPackageName).any()
}
