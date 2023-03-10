package com.moriatsushi.koject.processor

import com.moriatsushi.koject.processor.assert.assertCompileFailed
import com.moriatsushi.koject.processor.compiletesting.KotlinCompilationFactory
import com.moriatsushi.koject.processor.error.DuplicateProvidedException
import com.moriatsushi.koject.processor.error.NotProvidedException
import com.moriatsushi.koject.processor.error.WrongScopeException
import com.tschuchort.compiletesting.SourceFile
import kotlin.test.assertContains
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class DIProcessorKojectExtrasFailedTest {
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
        val location = "Test.kt:13"
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
        val location1 = "Test.kt:12"
        val location2 = "Test.kt:8"
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
        val location1 = "Test.kt:13"
        val location2 = "Test.kt:8"
        val expectedErrorMessage = "com.testpackage.SampleClass provide is duplicated."
        assertContains(result.messages, expectedError.qualifiedName!!)
        assertContains(result.messages, location1)
        assertContains(result.messages, location2)
        assertContains(result.messages, expectedErrorMessage)
    }

    @Test
    fun wrongScope() {
        val folder = tempFolder.newFolder()
        val complication = compilationFactory.create(folder)
        complication.sources = listOf(wrongScopeCode)
        val result = complication.compile()

        assertCompileFailed(result)

        val expectedError = WrongScopeException::class
        val location = "Test.kt:17"
        val expectedErrorMessage =
            "com.testpackage.SampleClass cannot be injected because it is not a singleton. " +
                "Only a singleton can be injected into singletons."
        assertContains(result.messages, expectedError.qualifiedName!!)
        assertContains(result.messages, location)
        assertContains(result.messages, expectedErrorMessage)
    }

    private val notProvidedInExtrasCode = SourceFile.kotlin(
        "Test.kt",
        """
                package com.testpackage

                import com.moriatsushi.koject.Provides
                import com.moriatsushi.koject.extras.KojectExtras
                
                @KojectExtras
                class GlobalExtras

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
                
                @KojectExtras
                class GlobalExtras(
                    val sampleClass: SampleClass
                )

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
                
                @KojectExtras
                class GlobalExtras1(
                    val sampleClass: SampleClass
                )

                @KojectExtras
                class GlobalExtras2(
                    val sampleClass: SampleClass
                )

                class SampleClass
            """,
    )

    private val wrongScopeCode = SourceFile.kotlin(
        "Test.kt",
        """
                package com.testpackage

                import com.moriatsushi.koject.Provides
                import com.moriatsushi.koject.Singleton
                import com.moriatsushi.koject.extras.KojectExtras

                class SampleClass
                
                @KojectExtras
                class GlobalExtras(
                    val sampleClass: SampleClass
                )

                @Singleton
                @Provides
                class SampleClassHolder(
                    val sampleClass: SampleClass
                )
            """,
    )
}
