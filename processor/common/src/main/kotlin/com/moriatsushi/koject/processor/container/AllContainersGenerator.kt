package com.moriatsushi.koject.processor.container

import com.google.devtools.ksp.processing.Resolver
import com.moriatsushi.koject.processor.analytics.includeTest
import com.moriatsushi.koject.processor.file.FileGenerator
import com.moriatsushi.koject.processor.symbol.ContainerDeclaration
import com.moriatsushi.koject.processor.symbol.collectAllDeclarations

internal class AllContainersGenerator(
    private val fileGenerator: FileGenerator,
    private val containerGenerator: ContainerGenerator,
    private val kojectFileSpecFactory: KojectFileSpecFactory,
    private val kojectTestFileSpecFactory: KojectTestFileSpecFactory,
) {
    fun generate(resolver: Resolver) {
        val allDeclaration = resolver.collectAllDeclarations()
        generateMainContainer(allDeclaration.mainContainer)
        if (resolver.includeTest()) {
            generateTestContainer(allDeclaration.testContainer)
        }
    }

    private fun generateMainContainer(
        container: ContainerDeclaration,
    ) {
        containerGenerator.generate(container)

        val kojectFileSpec = kojectFileSpecFactory.create()

        fileGenerator.createNewFile(
            fileSpec = kojectFileSpec,
            aggregating = false,
        )
    }

    private fun generateTestContainer(
        container: ContainerDeclaration,
    ) {
        containerGenerator.generate(container)

        val kojectTestFileSpec = kojectTestFileSpecFactory.create()

        fileGenerator.createNewFile(
            fileSpec = kojectTestFileSpec,
            aggregating = false,
        )
    }
}
