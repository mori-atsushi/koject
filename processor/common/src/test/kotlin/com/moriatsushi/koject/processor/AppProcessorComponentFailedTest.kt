package com.moriatsushi.koject.processor

import com.moriatsushi.koject.processor.assert.assertCompileFailed
import com.moriatsushi.koject.processor.compiletesting.KotlinCompilationFactory
import com.moriatsushi.koject.processor.error.CodeGenerationException
import com.moriatsushi.koject.processor.error.DuplicateComponentExtrasException
import com.moriatsushi.koject.processor.error.DuplicateProvidedException
import com.moriatsushi.koject.processor.error.NotProvidedException
import com.tschuchort.compiletesting.SourceFile
import kotlin.test.assertContains
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class AppProcessorComponentFailedTest {
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
        val location = "Test.kt:18"
        val expectedErrorMessage = "com.testpackage.NotProvided is not provided " +
            "in Component(com.testpackage.CustomComponent)."
        assertContains(result.messages, expectedError.qualifiedName!!)
        assertContains(result.messages, location)
        assertContains(result.messages, expectedErrorMessage)
    }

    @Test
    fun duplicateProvidedInComponent() {
        val folder = tempFolder.newFolder()
        val complication = compilationFactory.create(folder)
        complication.sources = listOf(duplicateProvidedInComponentCode)
        val result = complication.compile()

        assertCompileFailed(result)

        val expectedError = DuplicateProvidedException::class
        val location1 = "Test.kt:16"
        val location2 = "Test.kt:20"
        val expectedErrorMessage = "com.testpackage.SampleClass provide is duplicated " +
            "in Component(com.testpackage.CustomComponent)."
        assertContains(result.messages, expectedError.qualifiedName!!)
        assertContains(result.messages, location1)
        assertContains(result.messages, location2)
        assertContains(result.messages, expectedErrorMessage)
    }

    @Test
    fun duplicateProvidedInExtras() {
        val folder = tempFolder.newFolder()
        val complication = compilationFactory.create(folder)
        complication.sources = listOf(duplicateProvidedInExtrasCode)
        val result = complication.compile()

        assertCompileFailed(result)

        val expectedError = DuplicateProvidedException::class
        val location1 = "Test.kt:12"
        val location2 = "Test.kt:17"
        val expectedErrorMessage = "com.testpackage.SampleClass provide is duplicated " +
            "in Component(com.testpackage.CustomComponent)."
        assertContains(result.messages, expectedError.qualifiedName!!)
        assertContains(result.messages, location1)
        assertContains(result.messages, location2)
        assertContains(result.messages, expectedErrorMessage)
    }

    @Test
    fun duplicateExtras() {
        val folder = tempFolder.newFolder()
        val complication = compilationFactory.create(folder)
        complication.sources = listOf(duplicateExtrasCode)
        val result = complication.compile()

        assertCompileFailed(result)

        val expectedError = DuplicateComponentExtrasException::class
        val location1 = "Test.kt:10"
        val location2 = "Test.kt:12"
        val expectedErrorMessage =
            "com.testpackage.CustomComponent has a duplicate ComponentExtras definition."
        assertContains(result.messages, expectedError.qualifiedName!!)
        assertContains(result.messages, location1)
        assertContains(result.messages, location2)
        assertContains(result.messages, expectedErrorMessage)
    }

    @Test
    fun singletonInComponent() {
        val folder = tempFolder.newFolder()
        val complication = compilationFactory.create(folder)
        complication.sources = listOf(singletonInComponentCode)
        val result = complication.compile()

        assertCompileFailed(result)

        val expectedError = CodeGenerationException::class
        val location = "Test.kt:17"
        val expectedErrorMessage = "Component type cannot be Singleton"
        assertContains(result.messages, expectedError.qualifiedName!!)
        assertContains(result.messages, location)
        assertContains(result.messages, expectedErrorMessage)
    }

    @Test
    fun singletonExtra() {
        val folder = tempFolder.newFolder()
        val complication = compilationFactory.create(folder)
        complication.sources = listOf(singletonExtraCode)
        val result = complication.compile()

        assertCompileFailed(result)

        val expectedError = CodeGenerationException::class
        val location = "Test.kt:14"
        val expectedErrorMessage = "Component extras types cannot be a singleton."
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
                
                class CustomComponentExtras: ComponentExtras<CustomComponent>

                class NotProvided

                @CustomComponent
                @Provides
                class SampleClass(
                    private val notProvided: NotProvided
                )
            """,
    )

    private val duplicateProvidedInComponentCode = SourceFile.kotlin(
        "Test.kt",
        """
                package com.testpackage

                import com.moriatsushi.koject.Provides
                import com.moriatsushi.koject.component.Component
                import com.moriatsushi.koject.component.ComponentExtras

                @Component
                @Retention(AnnotationRetention.BINARY)
                annotation class CustomComponent
                
                class CustomComponentExtras: ComponentExtras<CustomComponent>

                class NotProvided

                @Provides
                class SampleClass

                @CustomComponent
                @Provides
                fun provideSampleClass(): SampleClass {
                    return SampleClass()
                }
            """,
    )

    private val duplicateProvidedInExtrasCode = SourceFile.kotlin(
        "Test.kt",
        """
                package com.testpackage

                import com.moriatsushi.koject.Provides
                import com.moriatsushi.koject.component.Component
                import com.moriatsushi.koject.component.ComponentExtras

                @Component
                @Retention(AnnotationRetention.BINARY)
                annotation class CustomComponent
                
                class CustomComponentExtras(
                    val sampleClass: SampleClass
                ): ComponentExtras<CustomComponent>

                @CustomComponent
                @Provides
                class SampleClass
            """,
    )

    private val duplicateExtrasCode = SourceFile.kotlin(
        "Test.kt",
        """
                package com.testpackage

                import com.moriatsushi.koject.component.Component
                import com.moriatsushi.koject.component.ComponentExtras

                @Component
                @Retention(AnnotationRetention.BINARY)
                annotation class CustomComponent
                
                class CustomComponentExtras1: ComponentExtras<CustomComponent>

                class CustomComponentExtras2: ComponentExtras<CustomComponent>
            """,
    )

    private val singletonInComponentCode = SourceFile.kotlin(
        "Test.kt",
        """
                package com.testpackage

                import com.moriatsushi.koject.Provides
                import com.moriatsushi.koject.Singleton
                import com.moriatsushi.koject.component.Component
                import com.moriatsushi.koject.component.ComponentExtras

                @Component
                @Retention(AnnotationRetention.BINARY)
                annotation class CustomComponent
                
                class CustomComponentExtras: ComponentExtras<CustomComponent>

                @CustomComponent
                @Singleton
                @Provides
                class SampleClass
            """,
    )

    private val singletonExtraCode = SourceFile.kotlin(
        "Test.kt",
        """
                package com.testpackage

                import com.moriatsushi.koject.Provides
                import com.moriatsushi.koject.Singleton
                import com.moriatsushi.koject.component.Component
                import com.moriatsushi.koject.component.ComponentExtras

                @Component
                @Retention(AnnotationRetention.BINARY)
                annotation class CustomComponent
                
                class CustomComponentExtras(
                    @Singleton
                    val extra: String
                ): ComponentExtras<CustomComponent>
            """,
    )
}
