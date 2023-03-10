package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.moriatsushi.koject.internal.Location
import com.squareup.kotlinpoet.ClassName

internal class ComponentExtrasHolderDeclaration(
    val componentName: ComponentName,
    val extrasHolder: ExtrasHolderDeclaration,
) {
    val className: ClassName
        get() = extrasHolder.className
    val extras: Sequence<Provided>
        get() = extrasHolder.extras
    val location: Location
        get() = extrasHolder.location
    val containingFile: KSFile?
        get() = extrasHolder.containingFile

    companion object
}

internal fun ComponentExtrasHolderDeclaration.Companion.of(
    ksClass: KSClassDeclaration,
): ComponentExtrasHolderDeclaration {
    return ComponentExtrasHolderDeclaration(
        componentName = ksClass.findStringComponentName()!!,
        extrasHolder = ExtrasHolderDeclaration.of(ksClass),
    )
}
