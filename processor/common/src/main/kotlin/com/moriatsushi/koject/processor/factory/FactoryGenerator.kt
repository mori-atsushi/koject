package com.moriatsushi.koject.processor.factory

import com.google.devtools.ksp.processing.Resolver
import com.moriatsushi.koject.processor.file.FileGenerator
import com.moriatsushi.koject.processor.symbol.ProviderDeclaration
import com.moriatsushi.koject.processor.symbol.findProviders

internal class FactoryGenerator(
    private val fileGenerator: FileGenerator,
    private val providerValidator: ProviderValidator,
    private val factoryFileSpecFactory: FactoryFileSpecFactory,
) {
    fun generate(resolver: Resolver) {
        val providers = resolver.findProviders()
        providers.forEach {
            processNode(it)
        }
    }

    private fun processNode(provider: ProviderDeclaration) {
        providerValidator.validate(provider)
        val fileSpec = factoryFileSpecFactory.create(provider)
        fileGenerator.createNewFile(fileSpec, false)
    }
}
