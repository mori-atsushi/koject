package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.moriatsushi.koject.processor.analytics.findIdentifier
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ksp.toClassName

class FactoryDeclaration(
    private val ksClass: KSClassDeclaration,
) {
    val identifier by lazy {
        ksClass.findIdentifier()!!
    }

    val containingFile: KSFile
        get() = ksClass.containingFile!!

    val parameters: List<ProviderParameter>
        get() = ksClass.primaryConstructor?.parameters
            .orEmpty()
            .map { ProviderParameter(it) }

    fun asClassName(): ClassName {
        return ksClass.toClassName()
    }
}
