package com.moriatsushi.koject.processor.code

import com.moriatsushi.koject.processor.assert.assertCompileSucceed
import com.moriatsushi.koject.processor.assert.assertFileExists
import com.moriatsushi.koject.processor.assert.assertFileTextEquals
import com.moriatsushi.koject.processor.compiletesting.KotlinCompilationFactory
import com.tschuchort.compiletesting.SourceFile
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class AnnotationNewInstanceCodeTest {
    @get:Rule
    val tempFolder: TemporaryFolder = TemporaryFolder()

    private val compilationFactory = KotlinCompilationFactory(
        listOf(AnnotationNewInstanceProcessor.Provider()),
    )

    private val generated = "ksp/sources/kotlin/generated.kt"

    @Test
    fun simpleAnnotation() {
        val folder = tempFolder.newFolder()
        val complication = compilationFactory.create(folder)
        val inputCode = SourceFile.kotlin(
            "Test.kt",
            """
                package com.testpackage

                annotation class Sample

                @Sample
                val value = 0
            """,
        )
        complication.sources = listOf(inputCode)
        val result = complication.compile()

        assertCompileSucceed(result)

        val generatedFile = folder.resolve(generated)
        assertFileExists(generatedFile)

        val expectedCode = """
            |import com.testpackage.Sample
            |
            |public val annotation0: Sample = Sample()
            |""".trimMargin()
        assertFileTextEquals(expectedCode, generatedFile)
    }
}
