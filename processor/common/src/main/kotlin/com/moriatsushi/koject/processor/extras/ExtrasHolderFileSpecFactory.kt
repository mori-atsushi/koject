package com.moriatsushi.koject.processor.extras

import com.moriatsushi.koject.processor.code.AnnotationSpecFactory
import com.moriatsushi.koject.processor.code.Names
import com.moriatsushi.koject.processor.code.applyCommon
import com.moriatsushi.koject.processor.symbol.ExtrasDeclaration
import com.moriatsushi.koject.processor.symbol.ExtrasParameter
import com.moriatsushi.koject.processor.symbol.asAnnotationSpec
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

internal class ExtrasHolderFileSpecFactory {
    fun generate(extrasDeclaration: ExtrasDeclaration): FileSpec {
        val classSpec = createClassSpec(extrasDeclaration)
        return FileSpec.builder(Names.extrasPackageName, classSpec.name!!).apply {
            applyCommon()
            addType(classSpec)
        }.build()
    }

    fun createClassSpec(extrasDeclaration: ExtrasDeclaration): TypeSpec {
        val name = "_${extrasDeclaration.asCodeName()}_Holder"
        return TypeSpec.classBuilder(name).apply {
            addAnnotation(AnnotationSpecFactory.createInternal())
            addAnnotation(extrasDeclaration.location.asAnnotationSpec())

            primaryConstructor(createConstructorSpec())
            addProperty(createExtrasPropertySpec(extrasDeclaration.className))

            extrasDeclaration.parameters.forEach {
                addProperty(createProvidePropertySpec(it))
            }

            addType(createCompanionObjectSpec(extrasDeclaration.className))

            extrasDeclaration.containingFile?.let {
                addOriginatingKSFile(it)
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
