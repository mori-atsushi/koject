package com.moriatsushi.koject.processor.container

import com.google.devtools.ksp.processing.Resolver
import com.moriatsushi.koject.processor.file.FileGenerator

internal class ContainerGenerator(
    private val fileGenerator: FileGenerator,
    private val containerFileSpecFactory: ContainerFileSpecFactory,
) {
    fun generate(resolver: Resolver) {
        // TODO: Impl
    }
}
