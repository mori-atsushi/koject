package com.moriatsushi.koject.processor.factory

import com.moriatsushi.koject.processor.dummy.DummyKSClassDeclarationFactory
import com.moriatsushi.koject.processor.dummy.DummyKSFile
import com.moriatsushi.koject.processor.symbol.ProviderDeclaration
import com.squareup.kotlinpoet.ksp.originatingKSFiles
import kotlin.test.assertEquals
import org.junit.Test

class FactoryFileSpecFactoryTest {
    @Test
    fun create() {
        val subject = FactoryFileSpecFactory()
        val containingFile = DummyKSFile.mainFile
        val ksClass = DummyKSClassDeclarationFactory.createProviderClass(
            simpleName = "Classname",
            containingFile = containingFile,
        )
        val provider = ProviderDeclaration(ksClass)

        val spec = subject.create(provider)

        val expectedCode = """
            |package com.moriatsushi.koject.di.generated.factory
            |
            |public class com_package_Classname_Factory
            |
        """.trimMargin()
        assertEquals(expectedCode, spec.toString())

        val expectedOriginatingKSFiles = listOf(containingFile)
        assertEquals(expectedOriginatingKSFiles, spec.originatingKSFiles())
    }
}
