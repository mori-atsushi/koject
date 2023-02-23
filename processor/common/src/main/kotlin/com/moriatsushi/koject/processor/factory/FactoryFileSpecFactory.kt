package com.moriatsushi.koject.processor.factory

import com.moriatsushi.koject.processor.symbol.PackageNames
import com.moriatsushi.koject.processor.symbol.ProviderDeclaration
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.ksp.addOriginatingKSFile

internal class FactoryFileSpecFactory {
    fun create(provider: ProviderDeclaration): FileSpec {
        return FileSpec.builder(
            packageName = PackageNames.factory,
            fileName = provider.factoryName,
        ).apply {
            addType(createClassSpec(provider))
        }.build()
    }

    private fun createClassSpec(provider: ProviderDeclaration): TypeSpec {
        return TypeSpec.classBuilder(provider.factoryName).apply {
            provider.ksClass.containingFile?.let {
                addOriginatingKSFile(it)
            }
        }.build()
    }
}
