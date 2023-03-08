package com.moriatsushi.koject.processor.container

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.processor.code.AnnotationSpecFactory
import com.moriatsushi.koject.processor.code.Names
import com.moriatsushi.koject.processor.code.applyCommon
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.asTypeName

internal class StartFileSpecFactory {
    fun create(): FileSpec {
        val packageName = Names.rootPackageName
        val containerName = Names.appContainerClassName

        val funSpec = FunSpec.builder("start").apply {
            receiver(Koject::class)
            // workaround to avoid crash in Kotlin/Native
            // https://github.com/square/kotlinpoet/issues/1273
            addStatement("%T._start(%T())", Koject::class.asTypeName(), containerName)
            addAnnotation(AnnotationSpecFactory.createOptInInternal())
        }.build()

        return FileSpec.builder(packageName, "_Start").apply {
            applyCommon()
            addFunction(funSpec)
        }.build()
    }
}
