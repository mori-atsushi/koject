package com.moriatsushi.koject.processor.factory

import com.moriatsushi.koject.internal.Identifier
import com.moriatsushi.koject.processor.analytics.primaryConstructorWithParameters
import com.moriatsushi.koject.processor.code.AnnotationSpecFactory
import com.moriatsushi.koject.processor.code.Names
import com.moriatsushi.koject.processor.code.applyCommon
import com.moriatsushi.koject.processor.symbol.ProviderDeclaration
import com.moriatsushi.koject.processor.symbol.asAnnotationSpec
import com.squareup.kotlinpoet.ANY
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.LambdaTypeName
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asTypeName
import com.squareup.kotlinpoet.buildCodeBlock
import com.squareup.kotlinpoet.ksp.addOriginatingKSFile

internal class FactoryFileSpecFactory {
    fun create(provider: ProviderDeclaration): FileSpec {
        val type = createClassSpec(provider)
        return FileSpec.builder(
            packageName = Names.factoryPackageName,
            fileName = type.name!!,
        ).apply {
            applyCommon()
            addType(type)
        }.build()
    }

    private fun createClassSpec(provider: ProviderDeclaration): TypeSpec {
        val factoryName = Names.factoryNameOf(provider.identifier.asStringIdentifier())
        val constructorSpec = createConstructorSpec(provider)
        val createFunSpec = createCreateFunSpec(provider)
        val internalAnnotationSpec =
            AnnotationSpecFactory.createInternal()
        val companionObject = createCompanionObjectSpec(provider)

        return TypeSpec.classBuilder(factoryName).apply {
            primaryConstructorWithParameters(
                constructorSpec,
                setOf(KModifier.PRIVATE),
            )
            addFunction(createFunSpec)
            addType(companionObject)
            addAnnotation(internalAnnotationSpec)
            if (provider.isSingleton) {
                addAnnotation(AnnotationSpecFactory.createSingleton())
            }
            addAnnotation(provider.identifier.asAnnotationSpec())
            addAnnotation(provider.location.asAnnotationSpec())

            provider.containingFile?.let {
                addOriginatingKSFile(it)
            }
        }.build()
    }

    private fun createConstructorSpec(provider: ProviderDeclaration): FunSpec {
        return FunSpec.constructorBuilder().apply {
            provider.parameters.forEach {
                val providerName = Names.providerNameOf(it.identifier.asStringIdentifier())
                val parameter = ParameterSpec.builder(
                    providerName,
                    LambdaTypeName.get(returnType = ANY),
                ).apply {
                    addAnnotation(it.identifier.asAnnotationSpec())
                    addAnnotation(it.location.asAnnotationSpec())
                }.build()
                addParameter(parameter)
            }
        }.build()
    }

    private fun createCreateFunSpec(provider: ProviderDeclaration): FunSpec {
        val code = buildCodeBlock {
            add("return ")
            when (provider) {
                is ProviderDeclaration.Class -> {
                    add("%T", provider.className)
                }
                is ProviderDeclaration.TopLevelFunction -> {
                    add("%M", provider.functionName)
                }
                is ProviderDeclaration.ObjectFunction -> {
                    add("%T.%M", provider.objectName, provider.functionName)
                }
            }
            add("(")
            if (provider.parameters.isNotEmpty()) {
                add("\n")
                indent()
                provider.parameters.forEach {
                    val providerName = Names.providerNameOf(it.identifier.asStringIdentifier())
                    add("$providerName() as %T,\n", it.identifier.typeName)
                }
                unindent()
            }
            add(")")
        }
        return FunSpec.builder("create").apply {
            returns(ANY)
            addCode(code)
        }.build()
    }

    private fun createCompanionObjectSpec(provider: ProviderDeclaration): TypeSpec {
        val initializerCode = buildCodeBlock {
            add("%T.of<%T>(", Identifier::class.asTypeName(), provider.identifier.typeName)
            val qualifier = provider.identifier.qualifier
            if (qualifier != null) {
                add("\n")
                indent()
                add(qualifier.newInstanceCode)
                unindent()
                add("\n")
            }
            add(")")
        }
        val identifierProperty = PropertySpec.builder("identifier", Identifier::class).apply {
            initializer(initializerCode)
        }.build()
        return TypeSpec.companionObjectBuilder().apply {
            addProperty(identifierProperty)
        }.build()
    }
}
