package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSNode
import com.moriatsushi.koject.internal.Location
import com.moriatsushi.koject.processor.analytics.findAnnotation
import com.moriatsushi.koject.processor.analytics.findArgumentByName
import com.moriatsushi.koject.processor.analytics.locationString
import com.squareup.kotlinpoet.AnnotationSpec

internal fun KSNode.createLocationAnnotation(): Location {
    return Location(locationString)
}

internal fun Location.asAnnotationSpec(): AnnotationSpec {
    return AnnotationSpec.builder(Location::class).apply {
        addMember("%S", value)
    }.build()
}

internal fun KSAnnotated.findLocationAnnotation(): Location? {
    val annotation = this.findAnnotation<Location>() ?: return null
    val value = annotation.findArgumentByName<String>("value")!!
    return Location(value)
}
