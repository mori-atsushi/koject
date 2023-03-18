package com.moriatsushi.koject.processor.factory

import com.moriatsushi.koject.internal.Identifier
import com.moriatsushi.koject.processor.code.AnnotationSpecFactory
import com.moriatsushi.koject.processor.code.Names
import com.moriatsushi.koject.processor.code.applyCommon
import com.moriatsushi.koject.processor.symbol.FactoryDeclaration
import com.moriatsushi.koject.processor.symbol.asAnnotationSpec
import com.squareup.kotlinpoet.ANY
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.LambdaTypeName
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.buildCodeBlock

internal class CopiedFactoryFileSpecFactory {
    fun create(factory: FactoryDeclaration): FileSpec {
        val type = createClassSpec(factory)
        return FileSpec.builder(
            packageName = Names.factoryPackageName,
            fileName = type.name!!,
        ).apply {
            applyCommon()
            addType(type)
        }.build()
    }

    private fun createClassSpec(factory: FactoryDeclaration): TypeSpec {
        val constructorSpec = createConstructorSpec(factory)
        val internalAnnotationSpec =
            AnnotationSpecFactory.createInternal()
        val companionObject = createCompanionObjectSpec(factory)

        return TypeSpec.classBuilder(factory.copiedName()).apply {
            primaryConstructor(constructorSpec)
            addProperty(createFactoryPropertySpec(factory))
            addFunction(createCreateFunSpec())
            addType(companionObject)
            addAnnotation(internalAnnotationSpec)
            if (factory.isSingleton) {
                addAnnotation(AnnotationSpecFactory.createSingleton())
            }
            if (factory.forTest) {
                addAnnotation(AnnotationSpecFactory.createForTest())
            }
            addAnnotation(factory.identifier.asAnnotationSpec())
            factory.component?.let {
                addAnnotation(it.asAnnotationSpec())
            }
            addAnnotation(factory.location.asAnnotationSpec())
        }.build()
    }

    private fun createFactoryPropertySpec(factory: FactoryDeclaration): PropertySpec {
        val code = buildCodeBlock {
            add("%T(", factory.className)
            if (factory.parameters.isNotEmpty()) {
                add("\n")
                indent()
                factory.parameters.forEach {
                    val providerName = Names.providerNameOf(it.identifier)
                    add("$providerName,\n")
                }
                unindent()
            }
            add(")")
        }

        return PropertySpec.builder(
            "factory",
            factory.className,
            KModifier.PRIVATE,
        ).apply {
            initializer(code)
        }.build()
    }

    private fun createConstructorSpec(factory: FactoryDeclaration): FunSpec {
        return FunSpec.constructorBuilder().apply {
            factory.parameters.forEach {
                val providerName = Names.providerNameOf(it.identifier)
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

    private fun createCreateFunSpec(): FunSpec {
        return FunSpec.builder("create").apply {
            returns(ANY)
            addCode("return factory.create()")
        }.build()
    }

    private fun createCompanionObjectSpec(factory: FactoryDeclaration): TypeSpec {
        val identifierProperty = PropertySpec.builder("identifier", Identifier::class).apply {
            initializer("%T.identifier", factory.className)
        }.build()
        return TypeSpec.companionObjectBuilder().apply {
            addProperty(identifierProperty)
        }.build()
    }
}
