package com.moriatsushi.koject.processor.container

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.KojectBuilder
import com.moriatsushi.koject.internal.KojectBuilderImpl
import com.moriatsushi.koject.processor.code.AnnotationSpecFactory
import com.moriatsushi.koject.processor.code.Names
import com.moriatsushi.koject.processor.code.applyCommon
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.LambdaTypeName
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.UNIT
import com.squareup.kotlinpoet.asTypeName

internal class StartFileSpecFactory {
    fun create(): FileSpec {
        val packageName = Names.rootPackageName
        val containerName = Names.appContainerClassName
        val builderLambdaType = LambdaTypeName.get(
            receiver = KojectBuilder::class.asTypeName(),
            returnType = UNIT,
        )
        val builderParameter = ParameterSpec.builder(
            "builder",
            builderLambdaType,
        ).apply {
            defaultValue("{}")
        }.build()

        val funSpec = FunSpec.builder("start").apply {
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

        return FileSpec.builder(packageName, "_Start").apply {
            applyCommon()
            addFunction(funSpec)
        }.build()
    }
}
