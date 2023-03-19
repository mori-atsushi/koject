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

class AppProcessorDependencyResolutionFailedTest {
    @get:Rule
    val tempFolder: TemporaryFolder = TemporaryFolder()

    private val compilationFactory = KotlinCompilationFactory()

    @Test
    fun notProvided() {
        val folder = tempFolder.newFolder()
        val complication = compilationFactory.create(folder)
        complication.sources = listOf(notProvidedInputCode)
        val result = complication.compile()

        assertCompileFailed(result)

        val expectedError = NotProvidedException::class
        val location = "Test.kt:9"
        val expectedErrorMessage = "com.testpackage.NotProvided is not provided."
        assertContains(result.messages, expectedError.qualifiedName!!)
        assertContains(result.messages, location)
        assertContains(result.messages, expectedErrorMessage)
    }

    @Test
    fun duplicateProvided() {
        val folder = tempFolder.newFolder()
        val complication = compilationFactory.create(folder)
        complication.sources = listOf(duplicateProvidedInputCode)
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

    @Test
    fun wrongScope() {
        val folder = tempFolder.newFolder()
        val complication = compilationFactory.create(folder)
        complication.sources = listOf(invalidScopeCode)
        val result = complication.compile()

        assertCompileFailed(result)

        val expectedError = WrongScopeException::class
        val location = "Test.kt:12"
        val expectedErrorMessage =
            "com.testpackage.NormalScope cannot be injected because it is not a singleton. " +
                "Only a singleton can be injected into singletons."
        assertContains(result.messages, expectedError.qualifiedName!!)
        assertContains(result.messages, location)
        assertContains(result.messages, expectedErrorMessage)
    }

    private val notProvidedInputCode = SourceFile.kotlin(
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

    private val duplicateProvidedInputCode = SourceFile.kotlin(
        "Test.kt",
        """
                package com.testpackage

                import com.moriatsushi.koject.Provides

                @Provides
                class SampleClass

                @Provides
                fun provideSampleClass(): SampleClass {
                    return SampleClass()
                }
            """,
    )

    private val invalidScopeCode = SourceFile.kotlin(
        "Test.kt",
        """
                package com.testpackage

                import com.moriatsushi.koject.Provides
                import com.moriatsushi.koject.Singleton

                @Provides
                class NormalScope

                @Singleton
                @Provides
                class SingletonScope(
                    val normal: NormalScope
                )
            """,
    )
}
