package com.moriatsushi.koject.processor.analysis

import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSAnnotation

internal inline fun <reified T> KSAnnotated.findAnnotation(): KSAnnotation? {
    return annotations.find {
        it.shortName.asString() == T::class.simpleName &&
                it.annotationType.resolve().declaration.qualifiedName!!.asString() ==
                T::class.qualifiedName!!
    }
}
