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

class DIProcessorProviderObjectTest {
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

        val expectedIntFactoryFile = folder.resolve(expectedIntFactoryFilePath)
        assertFileExists(expectedIntFactoryFile)

        val expectedClassFactoryFile = folder.resolve(expectedClassFactoryFilePath)
        assertFileExists(expectedClassFactoryFile)
    }

    private val inputCode = SourceFile.kotlin(
        "Test.kt",
        """
                package com.testpackage

                import com.moriatsushi.koject.Provides

                object ProviderObject {
                    @Provides
                    fun provideInt(): Int {
                        return 123
                    }
                
                    @Provides
                    fun provideWithParameters(
                        int: Int,
                    ): ProviderObjectWithParameters {
                        return ProviderObjectWithParameters(
                            int = int,
                        )
                    }
                }

                data class ProviderObjectWithParameters(
                    val int: Int,
                )
                """,
    )

    private val expectedIntFactoryFilePath =
        "ksp/sources/kotlin/com/moriatsushi/koject/generated/factory/" +
            "_kotlin_Int_Factory.kt"

    private val expectedClassFactoryFilePath =
        "ksp/sources/kotlin/com/moriatsushi/koject/generated/factory/" +
            "_com_testpackage_ProviderObjectWithParameters_Factory.kt"
}
