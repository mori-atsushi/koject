package com.moriatsushi.koject.processor

import com.moriatsushi.koject.processor.assert.assertCompileFailed
import com.moriatsushi.koject.processor.compiletesting.KotlinCompilationFactory
import com.moriatsushi.koject.processor.error.CodeGenerationException
import com.moriatsushi.koject.processor.error.NotProvidedException
import com.moriatsushi.koject.processor.error.WrongScopeException
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
    fun notProvided() {
        val folder = tempFolder.newFolder()
        val complication = compilationFactory.create(folder)
        complication.sources = listOf(notProvidedInputCode)
        val result = complication.compile()

        assertCompileFailed(result)

        val expectedError = NotProvidedException::class
        val expectedErrorMessage1 = "com.testpackage.NotProvided is not provided."
        val expectedErrorMessage2 = "It is requested by com.testpackage.SampleClass."
        assertContains(result.messages, expectedError.qualifiedName!!)
        assertContains(result.messages, expectedErrorMessage1)
        assertContains(result.messages, expectedErrorMessage2)
    }

    @Test
    fun wrongScope() {
        val folder = tempFolder.newFolder()
        val complication = compilationFactory.create(folder)
        complication.sources = listOf(invalidScopeCode)
        val result = complication.compile()

        assertCompileFailed(result)

        val expectedError = WrongScopeException::class
        val expectedErrorMessage1 =
            "com.testpackage.SingletonScope cannot be created " +
                    "because com.testpackage.NormalScope is not a singleton."
        val expectedErrorMessage2 =
            "Only a singleton can be requested from a singleton."
        assertContains(result.messages, expectedError.qualifiedName!!)
        assertContains(result.messages, expectedErrorMessage1)
        assertContains(result.messages, expectedErrorMessage2)
    }

    @Test
    fun notSupportedAnnotationMemberType() {
        val folder = tempFolder.newFolder()
        val complication = compilationFactory.create(folder)
        complication.sources = listOf(notSupportedAnnotationMemberTypeCode)
        val result = complication.compile()

        assertCompileFailed(result)

        val expectedError = CodeGenerationException::class
        val expectedErrorMessage1 =
            "java.util.ArrayList is an unsupported annotation member type."
        val expectedErrorMessage2 =
            "at com.testpackage.ArrayQualifier.array"
        assertContains(result.messages, expectedError.qualifiedName!!)
        assertContains(result.messages, expectedErrorMessage1)
        assertContains(result.messages, expectedErrorMessage2)
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

    private val notSupportedAnnotationMemberTypeCode = SourceFile.kotlin(
        "Test.kt",
        """
                package com.testpackage

                import com.moriatsushi.koject.Provides
                import com.moriatsushi.koject.Qualifier

                @Qualifier
                @Retention(AnnotationRetention.BINARY)
                annotation class ArrayQualifier(val array: Array<String>)

                @ArrayQualifier(["a", "b", "c"])
                @Provides
                fun provideString(): String = "not supported"
            """,
    )
}
