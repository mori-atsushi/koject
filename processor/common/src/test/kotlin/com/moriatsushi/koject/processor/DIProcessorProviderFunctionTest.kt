package com.moriatsushi.koject.processor

import com.moriatsushi.koject.processor.assert.assertCompileSucceed
import com.moriatsushi.koject.processor.assert.assertFileExists
import com.moriatsushi.koject.processor.compiletesting.KotlinCompilationFactory
import com.tschuchort.compiletesting.SourceFile
import java.io.File
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class DIProcessorProviderFunctionTest {
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

        val expectedStringFactoryFile = folder.resolve(expectedStringFactoryFilePath)
        assertFileExists(expectedStringFactoryFile)

        val expectedInterfaceFactoryFile = folder.resolve(expectedInterfaceFactoryFilePath)
        assertFileExists(expectedInterfaceFactoryFile)

        val expectedClassFactoryFile = folder.resolve(expectedClassFactoryFilePath)
        assertFileExists(expectedClassFactoryFile)

        val expectedContainerFile = folder.resolve(expectedContainerFilePath)
        assertFileExists(expectedContainerFile)
    }

    private val inputCode = SourceFile.kotlin(
        "Test.kt",
        """
                package com.testpackage

                import com.moriatsushi.koject.Provides

                @Provides
                fun provideString(): String {
                    return "provided"
                }

                @Provides
                fun provideInterface(): SampleInterface {
                    return object : SampleInterface {}
                }

                @Provides
                fun provideSampleClass(string: String, type: SampleInterface): SampleClass {
                    return SampleClass(string, type)
                }

                interface SampleInterface

                class SampleClass(
                    private val string: String,
                    private val type: SampleInterface
                )
            """,
    )

    private val expectedStringFactoryFilePath =
        "ksp/sources/kotlin/com/moriatsushi/koject/generated/factory/" +
            "_kotlin_String_Factory.kt"

    private val expectedInterfaceFactoryFilePath =
        "ksp/sources/kotlin/com/moriatsushi/koject/generated/factory/" +
            "_com_testpackage_SampleInterface_Factory.kt"

    private val expectedClassFactoryFilePath =
        "ksp/sources/kotlin/com/moriatsushi/koject/generated/factory/" +
            "_com_testpackage_SampleClass_Factory.kt"

    private val expectedContainerFilePath =
        "ksp/sources/kotlin/com/moriatsushi/koject/generated/" +
            "_AppContainer.kt"
}
