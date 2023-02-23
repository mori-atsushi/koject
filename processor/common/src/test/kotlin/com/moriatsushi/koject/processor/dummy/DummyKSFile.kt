package com.moriatsushi.koject.processor.dummy

import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSName
import com.google.devtools.ksp.symbol.KSNode
import com.google.devtools.ksp.symbol.KSVisitor
import com.google.devtools.ksp.symbol.Location
import com.google.devtools.ksp.symbol.NonExistLocation
import com.google.devtools.ksp.symbol.Origin

class DummyKSFile(
    override val annotations: Sequence<KSAnnotation>,
    override val declarations: Sequence<KSDeclaration>,
    override val fileName: String,
    override val filePath: String,
    override val packageName: KSName,
    override val location: Location,
    override val origin: Origin,
    override val parent: KSNode?,
) : KSFile {
    class Builder(
        var fileName: String,
    ) {
        val annotations = mutableListOf<KSAnnotation>()
        val declarations = mutableListOf<KSDeclaration>()
        var filePath: String = ""
        var packageName: KSName = DummyKSName()
        var location: Location = NonExistLocation
        var origin: Origin = Origin.KOTLIN
        var parent: KSNode? = null

        fun build(): DummyKSFile {
            return DummyKSFile(
                annotations = annotations.asSequence(),
                declarations = declarations.asSequence(),
                fileName = fileName,
                filePath = filePath,
                packageName = packageName,
                location = location,
                origin = origin,
                parent = parent,
            )
        }
    }

    companion object {
        val mainFile = dummyKSFile("main") {
            this.packageName = DummyKSName.rootPackageName
        }
    }

    override fun <D, R> accept(visitor: KSVisitor<D, R>, data: D): R {
        TODO()
    }
}

fun dummyKSFile(
    fileName: String,
    builder: DummyKSFile.Builder.() -> Unit,
): DummyKSFile {
    return DummyKSFile.Builder(fileName)
        .apply { builder() }
        .build()
}
