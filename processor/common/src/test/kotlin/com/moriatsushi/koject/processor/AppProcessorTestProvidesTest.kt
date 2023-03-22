package com.moriatsushi.koject.processor

import com.moriatsushi.koject.processor.assert.assertCompileFailed
import com.moriatsushi.koject.processor.assert.assertCompileSucceed
import com.moriatsushi.koject.processor.compiletesting.KotlinCompilationFactory
import com.moriatsushi.koject.processor.error.DuplicateProvidedException
import com.tschuchort.compiletesting.SourceFile
import java.io.File
import kotlin.test.assertContains
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class AppProcessorTestProvidesTest {
    @get:Rule
    val tempFolder: TemporaryFolder = TemporaryFolder()

    private val compilationFactory = KotlinCompilationFactory()
    private lateinit var folder: File

    @Before
    fun setup() {
        folder = tempFolder.newFolder()
    }

    @Test
    fun success_testProvides() {
        val complication = compilationFactory.create(folder)
        complication.sources = listOf(testProvidesCode)
        val result = complication.compile()

        assertCompileSucceed(result)
    }

    @Test
    fun success_override() {
        val complication = compilationFactory.create(folder)
        complication.sources = listOf(overrideCode)
        val result = complication.compile()

        assertCompileSucceed(result)
    }

    @Test
    fun failed_duplicateProvided() {
        val complication = compilationFactory.create(folder)
        complication.sources = listOf(duplicateProvidedCode)
        val result = complication.compile()

        assertCompileFailed(result)

        val expectedError = DuplicateProvidedException::class
        val location1 = "Test.kt:6"
        val location2 = "Test.kt:9"
        val expectedErrorMessage =
            "com.testpackage.SampleClass provide is duplicated."
        assertContains(result.messages, expectedError.qualifiedName!!)
        assertContains(result.messages, location1)
        assertContains(result.messages, location2)
        assertContains(result.messages, expectedErrorMessage)
    }

    private val testProvidesCode = SourceFile.kotlin(
        "Test.kt",
        """
                package com.testpackage

                import com.moriatsushi.koject.test.TestProvides

                @TestProvides
                class Sample
            """,
    )

    private val overrideCode = SourceFile.kotlin(
        "Test.kt",
        """
                package com.testpackage

                import com.moriatsushi.koject.Provides
                import com.moriatsushi.koject.Binds
                import com.moriatsushi.koject.test.TestProvides

                @Provides
                open class Sample

                @TestProvides
                @Binds
                class SampleTest : Sample
            """,
    )

    private val duplicateProvidedCode = SourceFile.kotlin(
        "Test.kt",
        """
                package com.testpackage

                import com.moriatsushi.koject.test.TestProvides

                @TestProvides
                class SampleClass

                @TestProvides
                fun provideSampleClass(): SampleClass {
                    return SampleClass()
                }
            """,
    )
}
