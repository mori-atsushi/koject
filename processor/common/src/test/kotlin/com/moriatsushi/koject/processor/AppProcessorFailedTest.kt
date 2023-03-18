package com.moriatsushi.koject.processor

import com.moriatsushi.koject.processor.assert.assertCompileFailed
import com.moriatsushi.koject.processor.compiletesting.KotlinCompilationFactory
import com.moriatsushi.koject.processor.error.CodeGenerationException
import com.tschuchort.compiletesting.SourceFile
import kotlin.test.assertContains
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class AppProcessorFailedTest {
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
    fun provideEnum() {
        val folder = tempFolder.newFolder()
        val complication = compilationFactory.create(folder)
        complication.sources = listOf(provideEnumCode)
        val result = complication.compile()

        assertCompileFailed(result)

        val expectedError = CodeGenerationException::class
        val location = "Test.kt:6"
        val expectedErrorMessage = "Enum class cannot be provided"
        assertContains(result.messages, expectedError.qualifiedName!!)
        assertContains(result.messages, location)
        assertContains(result.messages, expectedErrorMessage)
    }

    @Test
    fun provideAbstract() {
        val folder = tempFolder.newFolder()
        val complication = compilationFactory.create(folder)
        complication.sources = listOf(provideAbstractCode)
        val result = complication.compile()

        assertCompileFailed(result)

        val expectedError = CodeGenerationException::class
        val location = "Test.kt:6"
        val expectedErrorMessage = "Abstract class cannot be provided"
        assertContains(result.messages, expectedError.qualifiedName!!)
        assertContains(result.messages, location)
        assertContains(result.messages, expectedErrorMessage)
    }

    @Test
    fun provideClassMethod() {
        val folder = tempFolder.newFolder()
        val complication = compilationFactory.create(folder)
        complication.sources = listOf(provideClassMethod)
        val result = complication.compile()

        assertCompileFailed(result)

        val expectedError = CodeGenerationException::class
        val location = "Test.kt:7"
        val expectedErrorMessage =
            "Provide by function is only allowed for top-level functions or object functions."
        assertContains(result.messages, expectedError.qualifiedName!!)
        assertContains(result.messages, location)
        assertContains(result.messages, expectedErrorMessage)
    }

    @Test
    fun provideGenerics() {
        val folder = tempFolder.newFolder()
        val complication = compilationFactory.create(folder)
        complication.sources = listOf(provideGenericsCode)
        val result = complication.compile()

        assertCompileFailed(result)

        val expectedError = CodeGenerationException::class
        val location = "Test.kt:6"
        val expectedErrorMessage =
            "Generics class cannot be provided directly. Please use provide function."
        assertContains(result.messages, expectedError.qualifiedName!!)
        assertContains(result.messages, location)
        assertContains(result.messages, expectedErrorMessage)
    }

    @Test
    fun provideNullable() {
        val folder = tempFolder.newFolder()
        val complication = compilationFactory.create(folder)
        complication.sources = listOf(provideNullableCode)
        val result = complication.compile()

        assertCompileFailed(result)

        val expectedError = CodeGenerationException::class
        val location = "Test.kt:6"
        val expectedErrorMessage = "Cannot provide a nullable type"
        assertContains(result.messages, expectedError.qualifiedName!!)
        assertContains(result.messages, location)
        assertContains(result.messages, expectedErrorMessage)
    }

    @Test
    fun injectNullable() {
        val folder = tempFolder.newFolder()
        val complication = compilationFactory.create(folder)
        complication.sources = listOf(injectNullable)
        val result = complication.compile()

        assertCompileFailed(result)

        val expectedError = CodeGenerationException::class
        val location = "Test.kt:7"
        val expectedErrorMessage = "Cannot inject a nullable type"
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
                interface SampleInterface
            """,
    )

    private val provideEnumCode = SourceFile.kotlin(
        "Test.kt",
        """
                package com.testpackage

                import com.moriatsushi.koject.Provides

                @Provides
                enum class SampleEnum
            """,
    )

    private val provideAbstractCode = SourceFile.kotlin(
        "Test.kt",
        """
                package com.testpackage

                import com.moriatsushi.koject.Provides

                @Provides
                abstract class SampleEnum
            """,
    )

    private val provideGenericsCode = SourceFile.kotlin(
        "Test.kt",
        """
                package com.testpackage

                import com.moriatsushi.koject.Provides

                @Provides
                class SampleClass<T>
            """,
    )

    private val provideClassMethod = SourceFile.kotlin(
        "Test.kt",
        """
                package com.testpackage

                import com.moriatsushi.koject.Provides

                class SomeClass {
                    @Provides
                    fun provideString(): String {
                        return "not allowed"
                    }
                }
            """,
    )

    private val provideNullableCode = SourceFile.kotlin(
        "Test.kt",
        """
                package com.testpackage

                import com.moriatsushi.koject.Provides

                @Provides
                fun provideNullable(): String? {
                    return null
                }
            """,
    )

    private val injectNullable = SourceFile.kotlin(
        "Test.kt",
        """
                package com.testpackage

                import com.moriatsushi.koject.Provides

                @Provides
                class SampleClass(
                    val value: String?
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
