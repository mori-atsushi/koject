package com.moriatsushi.koject.processor

import com.moriatsushi.koject.processor.assert.assertCompileFailed
import com.moriatsushi.koject.processor.compiletesting.KotlinCompilationFactory
import com.moriatsushi.koject.processor.error.DependencyResolutionException
import com.tschuchort.compiletesting.SourceFile
import kotlin.test.assertContains
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class DIProcessorFailedTest {
    @get:Rule
    val tempFolder: TemporaryFolder = TemporaryFolder()

    private val compilationFactory = KotlinCompilationFactory()

    @Test
    fun compile() {
        val folder = tempFolder.newFolder()
        val complication = compilationFactory.create(folder)
        complication.sources = listOf(inputCode)
        val result = complication.compile()

        assertCompileFailed(result)

        val expectedError = DependencyResolutionException::class
        val expectedErrorMessage =
            "com.testpackage.NotProvided is not provided. " +
                "It is requested by com.testpackage.SampleClass."
        assertContains(result.messages, expectedError.qualifiedName!!)
        assertContains(result.messages, expectedErrorMessage)
    }

    private val inputCode = SourceFile.kotlin(
        "Test.kt",
        """
                package com.testpackage

                import com.moriatsushi.koject.Provides

                class NotProvided

                @Provides
                class SampleClass(
                    private val notProvided: NotProvided
                )
            """,
    )
}
