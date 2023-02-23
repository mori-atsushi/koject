package com.moriatsushi.koject.processor.dummy

import com.google.devtools.ksp.symbol.AnnotationUseSiteTarget
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSName
import com.google.devtools.ksp.symbol.KSNode
import com.google.devtools.ksp.symbol.KSTypeReference
import com.google.devtools.ksp.symbol.KSValueArgument
import com.google.devtools.ksp.symbol.KSVisitor
import com.google.devtools.ksp.symbol.Location
import com.google.devtools.ksp.symbol.NonExistLocation
import com.google.devtools.ksp.symbol.Origin

class DummyKSAnnotation(
    override val annotationType: KSTypeReference,
    override val arguments: List<KSValueArgument>,
    override val defaultArguments: List<KSValueArgument>,
    override val shortName: KSName,
    override val useSiteTarget: AnnotationUseSiteTarget?,
    override val location: Location,
    override val origin: Origin,
    override val parent: KSNode?,
) : KSAnnotation {
    class Builder(
        private val annotationType: KSTypeReference,
        private val shortName: KSName,
    ) {
        val arguments = mutableListOf<KSValueArgument>()
        val defaultArguments = mutableListOf<KSValueArgument>()
        var useSiteTarget: AnnotationUseSiteTarget? = null
        var location: Location = NonExistLocation
        var origin: Origin = Origin.KOTLIN
        var parent: KSNode? = null

        fun build(): DummyKSAnnotation {
            return DummyKSAnnotation(
                annotationType = annotationType,
                arguments = arguments,
                defaultArguments = defaultArguments,
                shortName = shortName,
                useSiteTarget = useSiteTarget,
                location = location,
                origin = origin,
                parent = parent,
            )
        }
    }

    override fun <D, R> accept(visitor: KSVisitor<D, R>, data: D): R {
        TODO()
    }
}

fun dummyKSAnnotation(
    annotationType: KSTypeReference,
    shortName: KSName,
    builder: DummyKSAnnotation.Builder.() -> Unit = {},
): DummyKSAnnotation {
    return DummyKSAnnotation.Builder(annotationType, shortName)
        .apply { builder() }
        .build()
}

inline fun <reified T> dummyKSAnnotation(
    crossinline builder: DummyKSAnnotation.Builder.() -> Unit = {},
): DummyKSAnnotation {
    val annotationType = dummyKSTypeReference<T>()
    val shortName = DummyKSName(string = T::class.simpleName.orEmpty())
    return dummyKSAnnotation(annotationType, shortName) {
        builder()
    }
}
