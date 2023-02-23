package com.moriatsushi.koject.processor.container

import com.moriatsushi.koject.internal.Container
import com.moriatsushi.koject.internal.identifier.Identifier
import com.moriatsushi.koject.processor.code.AnnotationSpecFactory
import com.moriatsushi.koject.processor.code.Names
import com.moriatsushi.koject.processor.code.applyCommon
import com.moriatsushi.koject.processor.symbol.FactoryDeclaration
import com.squareup.kotlinpoet.ANY
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.ksp.addOriginatingKSFile

internal class ContainerFileSpecFactory {
    fun create(factoryClasses: Sequence<FactoryDeclaration>): FileSpec {
        val typeSpec = createContainerClass(factoryClasses)

        return FileSpec.builder(
            Names.containerClassName.packageName,
            Names.containerClassName.simpleName,
        ).apply {
            applyCommon()
            addType(typeSpec)
        }.build()
    }

    private fun createContainerClass(factoryClasses: Sequence<FactoryDeclaration>): TypeSpec {
        val internalAnnotationSpec =
            AnnotationSpecFactory.createInternal()

        return TypeSpec.classBuilder(Names.containerClassName).apply {
            addSuperinterface(Container::class)
            factoryClasses.forEach {
                addFunction(createProviderFunSpec(it))
            }
            addFunction(createGetFunSpec(factoryClasses))
            addAnnotation(internalAnnotationSpec)

            factoryClasses
                .mapNotNull { it.containingFile }
                .forEach { addOriginatingKSFile(it) }
        }.build()
    }

    private fun createProviderFunSpec(factoryClass: FactoryDeclaration): FunSpec {
        val factoryName = factoryClass.asClassName()
        val providerName = Names.providerNameOf(factoryClass.identifier)
        val params = factoryClass.parameters.joinToString(",") {
            "\n::${it.providerName}"
        }

        return FunSpec.builder(providerName).apply {
            returns(ANY)
            addModifiers(KModifier.PRIVATE)
            addStatement("return %T($params\n).create()", factoryName)
        }.build()
    }

    private fun createGetFunSpec(
        factoryClasses: Sequence<FactoryDeclaration>,
    ): FunSpec {
        return FunSpec.builder("resolve").apply {
            returns(ANY)
            addModifiers(KModifier.OVERRIDE)
            addParameter("id", Identifier::class)
            beginControlFlow("return when (id.value) {")
            factoryClasses.forEach {
                val name = Names.providerNameOf(it.identifier)
                addStatement("%S -> $name()", it.identifier.value)
            }
            addStatement("else -> error(\"not provided\")")
            endControlFlow()
        }.build()
    }
}
