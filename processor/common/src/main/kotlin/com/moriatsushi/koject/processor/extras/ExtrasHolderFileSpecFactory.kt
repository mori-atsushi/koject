package com.moriatsushi.koject.processor.extras

import com.moriatsushi.koject.processor.code.AnnotationSpecFactory
import com.moriatsushi.koject.processor.code.Names
import com.moriatsushi.koject.processor.code.applyCommon
import com.moriatsushi.koject.processor.symbol.ExtraDeclaration
import com.moriatsushi.koject.processor.symbol.ExtrasDeclaration
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
import com.squareup.kotlinpoet.STRING
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asTypeName
import com.squareup.kotlinpoet.buildCodeBlock
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

            extrasDeclaration.extras.forEach {
                addProperty(createProvidePropertySpec(it))
            }

            addType(createCompanionObjectSpec(extrasDeclaration))

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

    private fun createProvidePropertySpec(parameter: ExtraDeclaration): PropertySpec {
        val name = Names.providerNameOf(parameter.identifier.asStringIdentifier())
        val type = LambdaTypeName.get(returnType = ANY)
        return PropertySpec.builder(name, type).apply {
            if (parameter.isSingleton) {
                delegate(
                    buildCodeBlock {
                        add("lazy·{\n")
                        indent()
                        add("val value = this.extras.${parameter.name}\n")
                        add("{ value }\n")
                        unindent()
                        add("}\n")
                    },
                )
                addAnnotation(AnnotationSpecFactory.createSingleton())
            } else {
                initializer("{ this.extras.${parameter.name} }")
            }
            addAnnotation(parameter.identifier.asAnnotationSpec())
            addAnnotation(parameter.location.asAnnotationSpec())
        }.build()
    }

    private fun createCompanionObjectSpec(extrasDeclaration: ExtrasDeclaration): TypeSpec {
        val className = extrasDeclaration.className
        val kClassType = KClass::class.asTypeName().parameterizedBy(STAR)

        val kClassProperty = PropertySpec.builder("kClass", kClassType).apply {
            initializer("%T::class", className)
        }.build()

        val nameProperty = PropertySpec.builder("name", STRING).apply {
            initializer("%S", className)
            addModifiers(KModifier.CONST)
        }.build()

        val messageProperty = PropertySpec.builder(
            "message",
            STRING.copy(nullable = true),
        ).apply {
            initializer("%S", extrasDeclaration.message)
        }.build()

        return TypeSpec.companionObjectBuilder().apply {
            addProperty(kClassProperty)
            addProperty(nameProperty)
            addProperty(messageProperty)
        }.build()
    }
}
