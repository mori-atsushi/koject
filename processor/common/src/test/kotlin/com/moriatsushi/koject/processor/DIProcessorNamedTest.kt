package com.moriatsushi.koject.processor

import com.moriatsushi.koject.processor.assert.assertCompileSucceed
import com.moriatsushi.koject.processor.compiletesting.KotlinCompilationFactory
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
                fun provideString2(): String {
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
}
