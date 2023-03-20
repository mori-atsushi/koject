package com.moriatsushi.koject.processor

import com.moriatsushi.koject.processor.assert.assertCompileFailed
import com.moriatsushi.koject.processor.assert.assertCompileSucceed
import com.moriatsushi.koject.processor.compiletesting.KotlinCompilationFactory
import com.moriatsushi.koject.processor.error.CodeGenerationException
import com.tschuchort.compiletesting.SourceFile
import java.io.File
import kotlin.test.assertContains
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class AppProcessorBindsTest {
    @get:Rule
    val tempFolder: TemporaryFolder = TemporaryFolder()

    private val compilationFactory = KotlinCompilationFactory()
    private lateinit var folder: File

    @Before
    fun setup() {
        folder = tempFolder.newFolder()
    }

    @Test
    fun success() {
        val complication = compilationFactory.create(folder)
        complication.sources = listOf(inputCode)
        val result = complication.compile()

        assertCompileSucceed(result)
    }

    @Test
    fun failed_notFoundSupertype() {
        val complication = compilationFactory.create(folder)
        complication.sources = listOf(notFoundSupertypeCode)
        val result = complication.compile()

        assertCompileFailed(result)

        val expectedError = CodeGenerationException::class
        val location = "Test.kt:8"
        val expectedErrorMessage = "com.testpackage.Sample super type not found."
        assertContains(result.messages, expectedError.qualifiedName!!)
        assertContains(result.messages, location)
        assertContains(result.messages, expectedErrorMessage)
    }

    @Test
    fun failed_notIncludedSupertype() {
        val complication = compilationFactory.create(folder)
        complication.sources = listOf(notIncludedSupertypeCode)
        val result = complication.compile()

        assertCompileFailed(result)

        val expectedError = CodeGenerationException::class
        val location = "Test.kt:8"
        val expectedErrorMessage = "com.testpackage.Interface2 is not included " +
            "in the super types of com.testpackage.Sample."
        assertContains(result.messages, expectedError.qualifiedName!!)
        assertContains(result.messages, location)
        assertContains(result.messages, expectedErrorMessage)
    }

    private val inputCode = SourceFile.kotlin(
        "Test.kt",
        """
                package com.testpackage

                import com.moriatsushi.koject.Binds
                import com.moriatsushi.koject.Provides

                @Binds
                @Provides
                class SampleImpl: Sample

                interface Sample
            """,
    )

    private val notFoundSupertypeCode = SourceFile.kotlin(
        "Test.kt",
        """
                package com.testpackage

                import com.moriatsushi.koject.Binds
                import com.moriatsushi.koject.Provides

                @Binds
                @Provides
                class Sample
            """,
    )

    private val notIncludedSupertypeCode = SourceFile.kotlin(
        "Test.kt",
        """
                package com.testpackage

                import com.moriatsushi.koject.Binds
                import com.moriatsushi.koject.Provides

                @Binds(to = Interface2::class)
                @Provides
                class Sample: Interface1

                interface Interface1
                interface Interface2
            """,
    )
}
