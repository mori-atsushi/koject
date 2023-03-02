package com.moriatsushi.koject.processor.code

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSAnnotation
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.ksp.toTypeName
import com.squareup.kotlinpoet.ksp.writeTo

class AnnotationNewInstanceProcessor(
    private val codeGenerator: CodeGenerator,
) : SymbolProcessor {
    private var invoked: Boolean = false
    override fun process(resolver: Resolver): List<KSAnnotated> {
        if (invoked) return emptyList()
        invoked = true

        val annotations = resolver.getNewFiles()
            .flatMap { it.declarations }
            .flatMap { it.annotations }
        generateCode(annotations)

        return emptyList()
    }

    private fun generateCode(annotations: Sequence<KSAnnotation>) {
        val file = FileSpec.builder("", "generated").apply {
            annotations.forEachIndexed { index, ksAnnotation ->
                val type = ksAnnotation.annotationType.toTypeName()
                val property = PropertySpec.builder("annotation$index", type).apply {
                    initializer(ksAnnotation.toNewInstanceCode())
                }.build()
                addProperty(property)
            }
        }.build()

        file.writeTo(codeGenerator, true)
    }

    class Provider : SymbolProcessorProvider {
        override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
            return AnnotationNewInstanceProcessor(
                environment.codeGenerator,
            )
        }
    }
}
