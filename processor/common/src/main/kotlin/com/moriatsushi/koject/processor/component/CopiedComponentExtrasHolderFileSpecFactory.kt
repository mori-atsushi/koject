package com.moriatsushi.koject.processor.component

import com.moriatsushi.koject.processor.code.AnnotationSpecFactory
import com.moriatsushi.koject.processor.code.Names
import com.moriatsushi.koject.processor.code.applyCommon
import com.moriatsushi.koject.processor.symbol.ComponentExtrasHolderDeclaration
import com.moriatsushi.koject.processor.symbol.Provided
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
import kotlin.reflect.KClass

internal class CopiedComponentExtrasHolderFileSpecFactory {
    fun generate(extrasDeclaration: ComponentExtrasHolderDeclaration): FileSpec {
        val classSpec = createClassSpec(extrasDeclaration)
        return FileSpec.builder(Names.componentPackageName, classSpec.name!!).apply {
            applyCommon()
            addType(classSpec)
        }.build()
    }

    private fun createClassSpec(
        extrasDeclaration: ComponentExtrasHolderDeclaration,
    ): TypeSpec {
        return TypeSpec.classBuilder(extrasDeclaration.copiedName()).apply {
            addAnnotation(AnnotationSpecFactory.createInternal())
            addAnnotation(extrasDeclaration.location.asAnnotationSpec())

            primaryConstructor(createConstructorSpec())
            addProperty(createHolderPropertySpec(extrasDeclaration.className))

            extrasDeclaration.extras.forEach {
                addProperty(createProvidePropertySpec(it))
            }

            addType(createCompanionObjectSpec(extrasDeclaration))

            addAnnotation(extrasDeclaration.componentName.asAnnotationSpec())
            addAnnotation(extrasDeclaration.createCopiedAnnotation())
        }.build()
    }

    private fun createConstructorSpec(): FunSpec {
        return FunSpec.constructorBuilder().apply {
            addParameter("extras", ANY)
        }.build()
    }

    private fun createHolderPropertySpec(className: ClassName): PropertySpec {
        return PropertySpec.builder("holder", className).apply {
            addModifiers(KModifier.PRIVATE)
            initializer("%T(extras)", className)
        }.build()
    }

    private fun createProvidePropertySpec(parameter: Provided): PropertySpec {
        val name = Names.providerNameOf(parameter.identifier)
        val type = LambdaTypeName.get(returnType = ANY)
        return PropertySpec.builder(name, type).apply {
            initializer("this.holder.$name")
            addAnnotation(parameter.identifier.asAnnotationSpec())
            addAnnotation(parameter.location.asAnnotationSpec())
        }.build()
    }

    private fun createCompanionObjectSpec(
        extrasDeclaration: ComponentExtrasHolderDeclaration,
    ): TypeSpec {
        val className = extrasDeclaration.className
        val kClassType = KClass::class.asTypeName().parameterizedBy(STAR)

        val kClassProperty = PropertySpec.builder("kClass", kClassType).apply {
            initializer("%T.kClass", className)
        }.build()

        return TypeSpec.companionObjectBuilder().apply {
            addProperty(kClassProperty)
        }.build()
    }
}
