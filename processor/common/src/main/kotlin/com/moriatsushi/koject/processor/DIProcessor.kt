package com.moriatsushi.koject.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.moriatsushi.koject.processor.container.ContainerGenerator
import com.moriatsushi.koject.processor.factory.FactoryGenerator

internal class DIProcessor(
    private val shouldGenerateContainer: Boolean,
    private val factoryGenerator: FactoryGenerator,
    private val containerGenerator: ContainerGenerator,
    private val codeGenerator: CodeGenerator,
) : SymbolProcessor {
    private var step: Step = Step.GenerateFactory

    override fun process(resolver: Resolver): List<KSAnnotated> {
        when (step) {
            Step.GenerateFactory -> {
                generateFactory(resolver)
            }
            Step.GenerateContainer -> {
                generateContainer(resolver)
            }
            Step.Completed -> {
                // no op
            }
        }

        return emptyList()
    }

    private fun generateFactory(resolver: Resolver) {
        factoryGenerator.generate(resolver)
        step = Step.GenerateContainer

        // container class will generate in the next cycle
        // if new files is generated.
        if (codeGenerator.generatedFile.isEmpty()) {
            generateContainer(resolver)
        }
    }

    private fun generateContainer(resolver: Resolver) {
        if (shouldGenerateContainer) {
            containerGenerator.generate(resolver)
        }
        step = Step.Completed
    }


    private enum class Step {
        GenerateFactory,
        GenerateContainer,
        Completed,
    }
}
