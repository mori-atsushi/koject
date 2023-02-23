package com.moriatsushi.koject.processor.container

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.moriatsushi.koject.processor.code.Names
import com.moriatsushi.koject.processor.file.FileGenerator
import com.moriatsushi.koject.processor.symbol.FactoryDeclaration

internal class ContainerGenerator(
    private val fileGenerator: FileGenerator,
    private val containerFileSpecFactory: ContainerFileSpecFactory,
) {
    fun generate(resolver: Resolver) {
        @OptIn(KspExperimental::class)
        val factoryClasses = resolver
            .getDeclarationsFromPackage(Names.factoryPackageName)
            .filterIsInstance<KSClassDeclaration>()
            .map { FactoryDeclaration(it) }
            .sortedBy { it.identifier }

        val fileSpec = containerFileSpecFactory.create(factoryClasses)

        fileGenerator.createNewFile(
            fileSpec = fileSpec,
            aggregating = true,
        )
    }
}
