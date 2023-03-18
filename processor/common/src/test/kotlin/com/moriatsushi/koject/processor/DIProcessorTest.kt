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

class DIProcessorTest {
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

        val expectedClass1FactoryFile = folder.resolve(expectedClass1FactoryFilePath)
        assertFileExists(expectedClass1FactoryFile)

        val expectedClass2FactoryFile = folder.resolve(expectedClass2FactoryFilePath)
        assertFileExists(expectedClass2FactoryFile)

        val expectedClass3FactoryFile = folder.resolve(expectedClass3FactoryFilePath)
        assertFileExists(expectedClass3FactoryFile)

        val expectedContainerFile = folder.resolve(expectedContainerFilePath)
        assertFileExists(expectedContainerFile)

        val expectedStartFile = folder.resolve(expectedStartFilePath)
        assertFileExists(expectedStartFile)
    }

    private val inputCode = SourceFile.kotlin(
        "Test.kt",
        """
                package com.testpackage

                import com.moriatsushi.koject.Provides

                @Provides
                class SampleClass1

                @Provides
                class SampleClass2(
                    private val sampleClass1: SampleClass1
                )

                @Provides
                class SampleClass3(
                    private val sampleClass1: SampleClass1,
                    private val sampleClass2: SampleClass2
                )
            """,
    )

    private val expectedClass1FactoryFilePath =
        "ksp/sources/kotlin/com/moriatsushi/koject/generated/factory/" +
            "_com_testpackage_SampleClass1_Factory.kt"

    private val expectedClass2FactoryFilePath =
        "ksp/sources/kotlin/com/moriatsushi/koject/generated/factory/" +
            "_com_testpackage_SampleClass2_Factory.kt"

    private val expectedClass3FactoryFilePath =
        "ksp/sources/kotlin/com/moriatsushi/koject/generated/factory/" +
            "_com_testpackage_SampleClass3_Factory.kt"

    private val expectedContainerFilePath =
        "ksp/sources/kotlin/com/moriatsushi/koject/generated/" +
            "_AppContainer.kt"

    private val expectedStartFilePath =
        "ksp/sources/kotlin/com/moriatsushi/koject/_Koject.kt"
}
