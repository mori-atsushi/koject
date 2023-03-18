package com.moriatsushi.koject.processor.container

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.KojectBuilder
import com.moriatsushi.koject.internal.KojectBuilderImpl
import com.moriatsushi.koject.processor.code.AnnotationSpecFactory
import com.moriatsushi.koject.processor.code.Names
import com.moriatsushi.koject.processor.code.applyCommon
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.LambdaTypeName
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.UNIT
import com.squareup.kotlinpoet.asTypeName

class KojectTestFileSpecFactory {
    private val packageName = Names.testPackageName
    private val containerName = ClassName(
        Names.generatedPackageName,
        "_TestContainer",
    )
    private val builderLambdaType = LambdaTypeName.get(
        receiver = KojectBuilder::class.asTypeName(),
        returnType = UNIT,
    )
    private val builderParameter = ParameterSpec.builder(
        "builder",
        builderLambdaType,
    ).apply {
        defaultValue("{}")
    }.build()

    fun create(): FileSpec {
        return FileSpec.builder(packageName, "_KojectTest").apply {
            applyCommon()
            addFunction(createStartTestFunSpec())
            addFunction(createRunTest())
        }.build()
    }

    private fun createStartTestFunSpec(): FunSpec {
        return FunSpec.builder("startTest").apply {
            receiver(Koject::class)
            addParameter(builderParameter)
            addStatement(
                "val extras = %T().apply(builder).extras",
                KojectBuilderImpl::class.asTypeName(),
            )
            addStatement("val container = %T(extras)", containerName)
            addStatement("%T._start(container)", Koject::class.asTypeName())
            addAnnotation(AnnotationSpecFactory.createOptInInternal())
        }.build()
    }

    private fun createRunTest(): FunSpec {
        val blockParameter = ParameterSpec.builder(
            "block",
            LambdaTypeName.get(returnType = UNIT),
        ).apply {
            defaultValue("{}")
        }.build()

        return FunSpec.builder("runTest").apply {
            receiver(Koject::class)
            addParameter(builderParameter)
            addParameter(blockParameter)
            addStatement("startTest(builder = builder)")
            addStatement("block()")
            addStatement("stop()")
            addAnnotation(AnnotationSpecFactory.createOptInInternal())
        }.build()
    }
}
