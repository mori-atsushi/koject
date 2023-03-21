package com.moriatsushi.koject.processor

import com.moriatsushi.koject.processor.assert.assertCompileFailed
import com.moriatsushi.koject.processor.compiletesting.KotlinCompilationFactory
import com.moriatsushi.koject.processor.error.DuplicateProvidedException
import com.moriatsushi.koject.processor.error.NotProvidedException
import com.tschuchort.compiletesting.SourceFile
import kotlin.test.assertContains
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class AppProcessorKojectExtrasFailedTest {
    @get:Rule
    val tempFolder: TemporaryFolder = TemporaryFolder()

    private val compilationFactory = KotlinCompilationFactory()

    @Test
    fun notProvidedInExtras() {
        val folder = tempFolder.newFolder()
        val complication = compilationFactory.create(folder)
        complication.sources = listOf(notProvidedInExtrasCode)
        val result = complication.compile()

        assertCompileFailed(result)

        val expectedError = NotProvidedException::class
        val location = "Test.kt:12"
        val expectedErrorMessage = "com.testpackage.NotProvided is not provided."
        assertContains(result.messages, expectedError.qualifiedName!!)
        assertContains(result.messages, location)
        assertContains(result.messages, expectedErrorMessage)
    }

    @Test
    fun duplicateProvided() {
        val folder = tempFolder.newFolder()
        val complication = compilationFactory.create(folder)
        complication.sources = listOf(duplicateProvidedCode)
        val result = complication.compile()

        assertCompileFailed(result)

        val expectedError = DuplicateProvidedException::class
        val location1 = "Test.kt:11"
        val location2 = "Test.kt:7"
        val expectedErrorMessage = "com.testpackage.SampleClass provide is duplicated."
        assertContains(result.messages, expectedError.qualifiedName!!)
        assertContains(result.messages, location1)
        assertContains(result.messages, location2)
        assertContains(result.messages, expectedErrorMessage)
    }

    @Test
    fun duplicateProvidedBetweenExtras() {
        val folder = tempFolder.newFolder()
        val complication = compilationFactory.create(folder)
        complication.sources = listOf(duplicateProvidedBetweenExtrasCode)
        val result = complication.compile()

        assertCompileFailed(result)

        val expectedError = DuplicateProvidedException::class
        val location1 = "Test.kt:11"
        val location2 = "Test.kt:7"
        val expectedErrorMessage = "com.testpackage.SampleClass provide is duplicated."
        assertContains(result.messages, expectedError.qualifiedName!!)
        assertContains(result.messages, location1)
        assertContains(result.messages, location2)
        assertContains(result.messages, expectedErrorMessage)
    }

    private val notProvidedInExtrasCode = SourceFile.kotlin(
        "Test.kt",
        """
                package com.testpackage

                import com.moriatsushi.koject.Provides
                import com.moriatsushi.koject.extras.KojectExtras
                
                class GlobalExtras: KojectExtras

                class NotProvided

                @Provides
                class SampleClass(
                    private val notProvided: NotProvided
                )
            """,
    )

    private val duplicateProvidedCode = SourceFile.kotlin(
        "Test.kt",
        """
                package com.testpackage

                import com.moriatsushi.koject.Provides
                import com.moriatsushi.koject.extras.KojectExtras

                class GlobalExtras(
                    val sampleClass: SampleClass
                ): KojectExtras

                @Provides
                class SampleClass
            """,
    )

    private val duplicateProvidedBetweenExtrasCode = SourceFile.kotlin(
        "Test.kt",
        """
                package com.testpackage

                import com.moriatsushi.koject.Provides
                import com.moriatsushi.koject.extras.KojectExtras

                class GlobalExtras1(
                    val sampleClass: SampleClass
                ): KojectExtras

                class GlobalExtras2(
                    val sampleClass: SampleClass
                ): KojectExtras

                class SampleClass
            """,
    )
}
