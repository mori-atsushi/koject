package com.moriatsushi.koject.processor

import com.moriatsushi.koject.processor.assert.assertCompileSucceed
import com.moriatsushi.koject.processor.assert.assertFileExists
import com.moriatsushi.koject.processor.assert.assertFileNotExists
import com.moriatsushi.koject.processor.compiletesting.KotlinCompilationFactory
import com.tschuchort.compiletesting.SourceFile
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class DIProcessorLibraryTest {
    @get:Rule
    val tempFolder: TemporaryFolder = TemporaryFolder()

    private val compilationFactory = KotlinCompilationFactory(
        listOf(TestProcessorProvider(shouldGenerateContainer = false)),
    )

    @Test
    fun compile() {
        val folder = tempFolder.newFolder()
        val complication = compilationFactory.create(folder)
        complication.sources = listOf(inputCode)
        val result = complication.compile()

        assertCompileSucceed(result)

        val expectedClass1FactoryFile = folder.resolve(expectedClass1FactoryFilePath)
        assertFileExists(expectedClass1FactoryFile)

        val expectedClass2FactoryFile = folder.resolve(expectedClass2FactoryFilePath)
        assertFileExists(expectedClass2FactoryFile)

        val expectedContainerFile = folder.resolve(expectedContainerFilePath)
        assertFileNotExists(expectedContainerFile)
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
            """,
    )

    private val expectedClass1FactoryFilePath =
        "ksp/sources/kotlin/com/moriatsushi/koject/di/generated/factory/" +
            "_com_testpackage_SampleClass1_Factory.kt"

    private val expectedClass2FactoryFilePath =
        "ksp/sources/kotlin/com/moriatsushi/koject/di/generated/factory/" +
            "_com_testpackage_SampleClass2_Factory.kt"

    private val expectedContainerFilePath =
        "ksp/sources/kotlin/com/moriatsushi/koject/di/generated/" +
            "_AppContainer.kt"
}
