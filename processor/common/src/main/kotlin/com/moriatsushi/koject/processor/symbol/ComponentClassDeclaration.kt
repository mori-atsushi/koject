package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.moriatsushi.koject.processor.code.Names
import com.moriatsushi.koject.processor.code.escapedForCode
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ksp.toClassName

internal data class ComponentClassDeclaration(
    val name: ComponentName,
    val className: ClassName,
    val containingFile: KSFile?,
) {
    companion object
}

internal val ComponentClassDeclaration.containerClassName: ClassName
    get() = ClassName(
        Names.generatedPackageName,
        "_${name.value.escapedForCode}_Container",
    )

internal fun ComponentClassDeclaration.Companion.of(
    ksClass: KSClassDeclaration,
): ComponentClassDeclaration {
    return ComponentClassDeclaration(
        name = ksClass.findStringComponentName()!!,
        className = ksClass.toClassName(),
        containingFile = ksClass.containingFile,
    )
}
