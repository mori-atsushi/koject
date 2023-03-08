package com.moriatsushi.koject.processor

import com.moriatsushi.koject.processor.assert.assertCompileFailed
import com.moriatsushi.koject.processor.compiletesting.KotlinCompilationFactory
import com.moriatsushi.koject.processor.error.NotProvidedException
import com.tschuchort.compiletesting.SourceFile
import kotlin.test.assertContains
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class DIProcessorComponentFailedTest {
    @get:Rule
    val tempFolder: TemporaryFolder = TemporaryFolder()

    private val compilationFactory = KotlinCompilationFactory()

    @Test
    fun notProvidedInComponent() {
        val folder = tempFolder.newFolder()
        val complication = compilationFactory.create(folder)
        complication.sources = listOf(notProvidedInComponentCode)
        val result = complication.compile()

        assertCompileFailed(result)

        val expectedError = NotProvidedException::class
        val location = "Test.kt:19"
        val expectedErrorMessage = "com.testpackage.NotProvided is not provided " +
            "in Component(com.testpackage.CustomComponent)."
        assertContains(result.messages, expectedError.qualifiedName!!)
        assertContains(result.messages, location)
        assertContains(result.messages, expectedErrorMessage)
    }

    private val notProvidedInComponentCode = SourceFile.kotlin(
        "Test.kt",
        """
                package com.testpackage

                import com.moriatsushi.koject.Provides
                import com.moriatsushi.koject.component.Component
                import com.moriatsushi.koject.component.ComponentExtras

                @Component
                @Retention(AnnotationRetention.BINARY)
                annotation class CustomComponent
                
                @ComponentExtras(CustomComponent::class)
                class CustomComponentExtras

                class NotProvided

                @CustomComponent
                @Provides
                class SampleClass(
                    private val notProvided: NotProvided
                )
            """,
    )
}
