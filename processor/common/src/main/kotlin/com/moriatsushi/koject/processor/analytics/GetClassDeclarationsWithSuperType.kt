package com.moriatsushi.koject.processor.analytics

import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSVisitorVoid

internal fun Resolver.getClassDeclarationsWithSuperType(
    superTypeName: String,
): Sequence<KSClassDeclaration> {
    val visitor = CollectSubclassDeclarationsVisitor(superTypeName)

    getNewFiles().forEach {
        it.accept(visitor, Unit)
    }

    return visitor.classes.asSequence()
}

private class CollectSubclassDeclarationsVisitor(
    private val superTypeName: String,
) : KSVisitorVoid() {
    val classes = arrayListOf<KSClassDeclaration>()

    override fun visitFile(file: KSFile, data: Unit) {
        file.declarations.forEach { it.accept(this, data) }
    }

    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
        if (classDeclaration.hasSuperType(superTypeName)) {
            classes.add(classDeclaration)
        }
        classDeclaration.declarations.forEach { it.accept(this, data) }
    }

    private fun KSClassDeclaration.hasSuperType(superTypeName: String): Boolean {
        return superTypes.any {
            it.resolve().declaration.qualifiedName?.asString() == superTypeName
        }
    }
}
