package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.moriatsushi.koject.Singleton
import com.moriatsushi.koject.internal.identifier.StringIdentifier
import com.moriatsushi.koject.processor.analytics.hasAnnotation
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ksp.toClassName

internal class FactoryDeclaration(
    private val ksClass: KSClassDeclaration,
) {
    val identifier: StringIdentifier by lazy {
        ksClass.findStringIdentifier()!!
    }

    val containingFile: KSFile?
        // null for other modules
        get() = ksClass.containingFile

    val parameters: List<ProviderParameter>
        get() = ksClass.primaryConstructor?.parameters
            .orEmpty()
            .map { ProviderParameter(it) }

    val isSingleton: Boolean
        get() = ksClass.hasAnnotation<Singleton>()

    fun asClassName(): ClassName {
        return ksClass.toClassName()
    }
}
