package com.moriatsushi.koject.processor

import com.moriatsushi.koject.processor.assert.assertCompileFailed
import com.moriatsushi.koject.processor.compiletesting.KotlinCompilationFactory
import com.moriatsushi.koject.processor.error.CodeGenerationException
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
    fun provideInterface() {
        val folder = tempFolder.newFolder()
        val complication = compilationFactory.create(folder)
        complication.sources = listOf(provideInterfaceCode)
        val result = complication.compile()

        assertCompileFailed(result)

        val expectedError = CodeGenerationException::class
        val location = "Test.kt:6"
        val expectedErrorMessage = "Interface cannot be provided"
        assertContains(result.messages, expectedError.qualifiedName!!)
        assertContains(result.messages, location)
        assertContains(result.messages, expectedErrorMessage)
    }

    @Test
    fun notSupportedAnnotationMemberType() {
        val folder = tempFolder.newFolder()
        val complication = compilationFactory.create(folder)
        complication.sources = listOf(notSupportedAnnotationMemberTypeCode)
        val result = complication.compile()

        assertCompileFailed(result)

        val expectedError = CodeGenerationException::class
        val expectedErrorMessage =
            "java.util.ArrayList is an unsupported annotation member type."
        assertContains(result.messages, expectedError.qualifiedName!!)
        assertContains(result.messages, expectedErrorMessage)
    }

    private val provideInterfaceCode = SourceFile.kotlin(
        "Test.kt",
        """
                package com.testpackage

                import com.moriatsushi.koject.Provides

                @Provides
                interface SampleClass
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
