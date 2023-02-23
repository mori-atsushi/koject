package com.moriatsushi.koject.processor.container

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.processor.code.AnnotationSpecFactory
import com.moriatsushi.koject.processor.code.Names
import com.moriatsushi.koject.processor.code.applyCommon
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec

internal class StartFileSpecFactory {
    fun create(): FileSpec {
        val packageName = Names.rootPackageName
        val containerName = Names.containerClassName

        val funSpec = FunSpec.builder("start").apply {
            receiver(Koject::class)
            addStatement("%T.start(%T())", Koject::class, containerName)
            addAnnotation(AnnotationSpecFactory.createOptInInternal())
        }.build()

        return FileSpec.builder(packageName, "_Start").apply {
            applyCommon()
            addFunction(funSpec)
        }.build()
    }
}
