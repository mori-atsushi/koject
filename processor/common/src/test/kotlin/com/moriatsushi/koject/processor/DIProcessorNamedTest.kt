package com.moriatsushi.koject.processor

import com.moriatsushi.koject.internal.StringIdentifier
import com.moriatsushi.koject.processor.assert.assertCompileSucceed
import com.moriatsushi.koject.processor.assert.assertFileExists
import com.moriatsushi.koject.processor.compiletesting.KotlinCompilationFactory
import com.moriatsushi.koject.processor.symbol.asCodeName
import com.tschuchort.compiletesting.SourceFile
import java.io.File
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class DIProcessorNamedTest {
    @get:Rule
    val tempFolder: TemporaryFolder = TemporaryFolder()

    private val compilationFactory = KotlinCompilationFactory()
    private lateinit var folder: File

    @Before
    fun setup() {
        folder = tempFolder.newFolder()
    }

    @Test
    fun compile() {
        val complication = compilationFactory.create(folder)
        complication.sources = listOf(inputCode)
        val result = complication.compile()

        assertCompileSucceed(result)

        val expectedClass1FactoryFile = folder.resolve(expectedName1FactoryFilePath)
        assertFileExists(expectedClass1FactoryFile)

        val expectedClass2FactoryFile = folder.resolve(expectedClassFactoryFilePath)
        assertFileExists(expectedClass2FactoryFile)

        val expectedClass3FactoryFile = folder.resolve(expectedFunctionFactoryFilePath)
        assertFileExists(expectedClass3FactoryFile)
    }

    private val inputCode = SourceFile.kotlin(
        "Test.kt",
        """
                package com.testpackage

                import com.moriatsushi.koject.Named
                import com.moriatsushi.koject.Provides

                @Named("name1")
                @Provides
                fun provideString1(): String {
                    return "name1"
                }

                @Named("name2")
                @Provides
                fun provideString1(): String {
                    return "name2"
                }

                @Provides
                class SampleClass(
                    @Named("name1")
                    private val name1: String,
                    @Named("name2")
                    private val name2: String,
                )

                @Named("by_function")
                @Provides
                fun provideSampleClass(
                    @Named("name1") name1: String,
                    @Named("name2") name2: String,
                ): SampleClass {
                    return SampleClass(name1, name2)
                }
            """,
    )

    private val expectedName1Identifier = StringIdentifier("kotlin.String", "Named(name1)")
    private val expectedName2Identifier = StringIdentifier("kotlin.String", "Named(name2)")

    private val expectedName1FactoryFilePath =
        "ksp/sources/kotlin/com/moriatsushi/koject/generated/factory/" +
            "_${expectedName1Identifier.asCodeName()}_Factory.kt"

    private val expectedClassFactoryFilePath =
        "ksp/sources/kotlin/com/moriatsushi/koject/generated/factory/" +
            "_com_testpackage_SampleClass_Factory.kt"

    private val expectedFunctionIdentifier =
        StringIdentifier("com.testpackage.SampleClass", "Named(by_function)")
    private val expectedFunctionFactoryFilePath =
        "ksp/sources/kotlin/com/moriatsushi/koject/generated/factory/" +
            "_${expectedFunctionIdentifier.asCodeName()}_Factory.kt"
}
