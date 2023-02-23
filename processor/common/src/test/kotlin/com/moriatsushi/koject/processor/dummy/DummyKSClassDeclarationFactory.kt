package com.moriatsushi.koject.processor.dummy

import com.google.devtools.ksp.symbol.KSFile
import com.moriatsushi.koject.Provides

object DummyKSClassDeclarationFactory {
    private val providesAnnotation =
        dummyKSAnnotation<Provides>()

    fun createProviderClass(
        simpleName: String,
        packageName: String = DummyKSName.rootPackageName.asString(),
        containingFile: KSFile = DummyKSFile.mainFile,
    ): DummyKSClassDeclaration {
        return dummyKSClassDeclaration(
            simpleName = DummyKSName(simpleName),
            packageName = DummyKSName(packageName),
        ) {
            this.qualifiedName = DummyKSName("$packageName.$simpleName")
            this.containingFile = containingFile
            this.annotations.add(providesAnnotation)
        }
    }
}
