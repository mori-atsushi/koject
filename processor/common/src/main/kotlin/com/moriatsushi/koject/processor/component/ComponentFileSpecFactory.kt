package com.moriatsushi.koject.processor.component

import com.moriatsushi.koject.processor.code.AnnotationSpecFactory
import com.moriatsushi.koject.processor.code.Names
import com.moriatsushi.koject.processor.code.applyCommon
import com.moriatsushi.koject.processor.symbol.ComponentDeclaration
import com.moriatsushi.koject.processor.symbol.ExtrasParameter
import com.moriatsushi.koject.processor.symbol.asAnnotationSpec
import com.moriatsushi.koject.processor.symbol.asCodeName
import com.squareup.kotlinpoet.ANY
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.LambdaTypeName
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.STAR
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asTypeName
import com.squareup.kotlinpoet.ksp.addOriginatingKSFile
import kotlin.reflect.KClass

internal class ComponentFileSpecFactory {
    fun generate(declaration: ComponentDeclaration): FileSpec {
        val classSpec = createComponentClassSpec(declaration)
        return FileSpec.builder(Names.componentPackageName, classSpec.name!!).apply {
            applyCommon()
            addType(classSpec)
        }.build()
    }

    private fun createComponentClassSpec(declaration: ComponentDeclaration): TypeSpec {
        val name = "_${declaration.asCodeName()}"
        return TypeSpec.classBuilder(name).apply {
            addAnnotation(AnnotationSpecFactory.createInternal())
            addAnnotation(declaration.name.asAnnotationSpec())

            primaryConstructor(createConstructorSpec())
            addProperty(createExtrasPropertySpec(declaration.extras.name))

            declaration.extras.parameters.forEach {
                addProperty(createProvidePropertySpec(it))
            }

            addType(createCompanionObjectSpec(declaration.extras.name))

            if (declaration.containingFile != null) {
                addOriginatingKSFile(declaration.containingFile)
            }
        }.build()
    }

    private fun createConstructorSpec(): FunSpec {
        return FunSpec.constructorBuilder().apply {
            addParameter("extras", ANY)
        }.build()
    }

    private fun createExtrasPropertySpec(className: ClassName): PropertySpec {
        return PropertySpec.builder("extras", className).apply {
            addModifiers(KModifier.PRIVATE)
            initializer("extras as %T", className)
        }.build()
    }

    private fun createProvidePropertySpec(parameter: ExtrasParameter): PropertySpec {
        val name = Names.providerNameOf(parameter.identifier.asStringIdentifier())
        val type = LambdaTypeName.get(returnType = ANY)
        return PropertySpec.builder(name, type).apply {
            initializer("{ this.extras.${parameter.name} }")
            addAnnotation(parameter.identifier.asAnnotationSpec())
            addAnnotation(parameter.location.asAnnotationSpec())
        }.build()
    }

    private fun createCompanionObjectSpec(className: ClassName): TypeSpec {
        val type = KClass::class.asTypeName().parameterizedBy(STAR)
        val property = PropertySpec.builder("argumentClass", type).apply {
            initializer("%T::class", className)
        }.build()

        return TypeSpec.companionObjectBuilder().apply {
            addProperty(property)
        }.build()
    }
}
