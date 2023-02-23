package com.moriatsushi.koject.processor.dummy

import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSNode
import com.google.devtools.ksp.symbol.KSReferenceElement
import com.google.devtools.ksp.symbol.KSType
import com.google.devtools.ksp.symbol.KSTypeReference
import com.google.devtools.ksp.symbol.KSVisitor
import com.google.devtools.ksp.symbol.Location
import com.google.devtools.ksp.symbol.Modifier
import com.google.devtools.ksp.symbol.NonExistLocation
import com.google.devtools.ksp.symbol.Origin

class DummyKSTypeReference(
    private val type: KSType,
    override val element: KSReferenceElement?,
    override val location: Location,
    override val modifiers: Set<Modifier>,
    override val origin: Origin,
    override val parent: KSNode?,
) : KSTypeReference {
    class Builder(
        private val type: KSType,
    ) {
        var element: KSReferenceElement? = null
        var location: Location = NonExistLocation
        val modifiers = mutableSetOf<Modifier>()
        var origin: Origin = Origin.KOTLIN
        var parent: KSNode? = null

        fun build(): DummyKSTypeReference {
            return DummyKSTypeReference(
                type = type,
                element = element,
                location = location,
                modifiers = modifiers,
                origin = origin,
                parent = parent,
            )
        }
    }

    override val annotations: Sequence<KSAnnotation>
        get() = type.annotations

    override fun <D, R> accept(visitor: KSVisitor<D, R>, data: D): R {
        TODO()
    }

    override fun resolve(): KSType {
        return type
    }
}

inline fun <reified T> dummyKSTypeReference(
    crossinline builder: DummyKSTypeReference.Builder.() -> Unit = {},
): DummyKSTypeReference {
    val type = dummyKSType<T>()
    return dummyKSTypeReference(type) {
        builder()
    }
}

fun dummyKSTypeReference(
    type: KSType,
    builder: DummyKSTypeReference.Builder.() -> Unit = {},
): DummyKSTypeReference {
    return DummyKSTypeReference.Builder(type)
        .apply { builder() }
        .build()
}
