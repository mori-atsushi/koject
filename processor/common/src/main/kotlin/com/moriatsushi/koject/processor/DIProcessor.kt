package com.moriatsushi.koject.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.moriatsushi.koject.processor.component.ComponentExtrasHolderGenerator
import com.moriatsushi.koject.processor.container.AllContainersGenerator
import com.moriatsushi.koject.processor.extras.ExtrasHolderGenerator
import com.moriatsushi.koject.processor.factory.FactoryGenerator
import kotlin.time.ExperimentalTime
import kotlin.time.TimeSource.Monotonic.markNow

@OptIn(ExperimentalTime::class)
internal class DIProcessor(
    private val options: DIProcessorOptions,
    private val factoryGenerator: FactoryGenerator,
    private val extrasHolderGenerator: ExtrasHolderGenerator,
    private val componentExtrasHolderGenerator: ComponentExtrasHolderGenerator,
    private val allContainerGenerator: AllContainersGenerator,
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger,
) : SymbolProcessor {
    private var step: Step = Step.CollectDependencies

    override fun process(resolver: Resolver): List<KSAnnotated> {
        measureTime(step.name) {
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

    private inline fun measureTime(
        label: String,
        crossinline block: () -> Unit,
    ) {
        if (!options.measureDuration) {
            block()
            return
        }

        val mark = markNow()
        block()
        val duration = mark.elapsedNow()
        logger.info("$label: $duration")
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
        if (options.shouldGenerateContainer) {
            allContainerGenerator.generate(resolver)
        }
        step = Step.Completed
    }

    private enum class Step {
        CollectDependencies,
        GenerateContainer,
        Completed,
    }
}
