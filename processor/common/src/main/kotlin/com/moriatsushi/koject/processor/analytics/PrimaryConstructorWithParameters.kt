package com.moriatsushi.koject.processor.analytics

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec

/**
 * Set primary constructor and set all its parameters as properties.
 */
fun TypeSpec.Builder.primaryConstructorWithParameters(
    primaryConstructor: FunSpec,
    modifiers: Iterable<KModifier> = emptySet(),
): TypeSpec.Builder {
    return apply {
        primaryConstructor(primaryConstructor)
        primaryConstructor.parameters.forEach {
            addProperty(
                PropertySpec.builder(it.name, it.type)
                    .initializer(it.name)
                    .addModifiers(modifiers)
                    .build(),
            )
        }
    }
}
