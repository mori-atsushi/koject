package com.moriatsushi.koject.processor

import com.moriatsushi.koject.processor.assert.assertCompileSucceed
import com.moriatsushi.koject.processor.compiletesting.KotlinCompilationFactory
import com.tschuchort.compiletesting.SourceFile
import java.io.File
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class AppProcessorProviderFunctionTest {
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
}
