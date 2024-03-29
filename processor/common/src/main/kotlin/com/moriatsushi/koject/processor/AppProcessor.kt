package com.moriatsushi.koject.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.moriatsushi.koject.processor.component.ComponentExtrasHolderGenerator
import com.moriatsushi.koject.processor.container.AllContainersGenerator
import com.moriatsushi.koject.processor.debug.TimeMeasure
import com.moriatsushi.koject.processor.extras.ExtrasHolderGenerator
import com.moriatsushi.koject.processor.factory.FactoryGenerator

internal class AppProcessor(
    private val factoryGenerator: FactoryGenerator,
    private val extrasHolderGenerator: ExtrasHolderGenerator,
    private val componentExtrasHolderGenerator: ComponentExtrasHolderGenerator,
    private val allContainerGenerator: AllContainersGenerator,
    private val codeGenerator: CodeGenerator,
    private val timeMeasure: TimeMeasure,
) : SymbolProcessor {
    private var step: Step = Step.CollectDependencies

    override fun process(resolver: Resolver): List<KSAnnotated> {
        timeMeasure.measure(step.name) {
            when (step) {
                Step.CollectDependencies -> {
                    collectDependencies(resolver)
                }
                Step.GenerateContainer -> {
                    generateContainer(resolver)
                }
                Step.Completed -> {
                    // no op
                }
            }
        }

        return emptyList()
    }

    private fun collectDependencies(resolver: Resolver) {
        factoryGenerator.generate(resolver)
        extrasHolderGenerator.generate(resolver)
        componentExtrasHolderGenerator.generate(resolver)
        step = Step.GenerateContainer

        // container class will generate in the next cycle
        // if new files is generated.
        if (codeGenerator.generatedFile.isEmpty()) {
            generateContainer(resolver)
        }
    }

    private fun generateContainer(resolver: Resolver) {
        allContainerGenerator.generate(resolver)
        step = Step.Completed
    }

    private enum class Step {
        CollectDependencies,
        GenerateContainer,
        Completed,
    }
}
