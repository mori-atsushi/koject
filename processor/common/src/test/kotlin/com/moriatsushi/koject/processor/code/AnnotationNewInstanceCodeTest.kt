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

                annotation class SimpleAnnotation

                @SimpleAnnotation
                val value = 0
            """,
        )
        complication.sources = listOf(inputCode)
        val result = complication.compile()

        assertCompileSucceed(result)

        val generatedFile = folder.resolve(generated)
        assertFileExists(generatedFile)

        val expectedCode = """
            |import com.testpackage.SimpleAnnotation
            |
            |public val annotation0: SimpleAnnotation = SimpleAnnotation()
            |""".trimMargin()
        assertFileTextEquals(expectedCode, generatedFile)
    }

    @Test
    fun longAnnotation() {
        val folder = tempFolder.newFolder()
        val complication = compilationFactory.create(folder)
        val inputCode = SourceFile.kotlin(
            "Test.kt",
            """
                package com.testpackage

                annotation class LongAnnotation(val value: Long)

                @LongAnnotation(100L)
                val value = 0
            """,
        )
        complication.sources = listOf(inputCode)
        val result = complication.compile()

        assertCompileSucceed(result)

        val generatedFile = folder.resolve(generated)
        assertFileExists(generatedFile)

        val expectedCode = """
            |import com.testpackage.LongAnnotation
            |
            |public val annotation0: LongAnnotation = LongAnnotation(value = 100)
            |""".trimMargin()
        assertFileTextEquals(expectedCode, generatedFile)
    }

    @Test
    fun enumAnnotation() {
        val folder = tempFolder.newFolder()
        val complication = compilationFactory.create(folder)
        val inputCode = SourceFile.kotlin(
            "Test.kt",
            """
                package com.testpackage

                annotation class EnumAnnotation(val value: SampleEnum)

                enum class SampleEnum {
                    TypeA,
                    TypeB;
                }

                @EnumAnnotation(SampleEnum.TypeA)
                val value = 0
            """,
        )
        complication.sources = listOf(inputCode)
        val result = complication.compile()

        assertCompileSucceed(result)

        val generatedFile = folder.resolve(generated)
        assertFileExists(generatedFile)

        val expectedCode = """
            |import com.testpackage.EnumAnnotation
            |import com.testpackage.SampleEnum
            |
            |public val annotation0: EnumAnnotation = EnumAnnotation(value = SampleEnum.TypeA)
            |""".trimMargin()
        assertFileTextEquals(expectedCode, generatedFile)
    }

    @Test
    fun classAnnotation() {
        val folder = tempFolder.newFolder()
        val complication = compilationFactory.create(folder)
        val inputCode = SourceFile.kotlin(
            "Test.kt",
            """
                package com.testpackage

                annotation class ClassAnnotation(val value: KClass<*>)

                class Sample

                @ClassAnnotation(Sample::class)
                val value = 0
            """,
        )
        complication.sources = listOf(inputCode)
        val result = complication.compile()

        assertCompileSucceed(result)

        val generatedFile = folder.resolve(generated)
        assertFileExists(generatedFile)

        val expectedCode = """
            |import com.testpackage.ClassAnnotation
            |import com.testpackage.Sample
            |
            |public val annotation0: ClassAnnotation = ClassAnnotation(value = Sample::class)
            |""".trimMargin()
        assertFileTextEquals(expectedCode, generatedFile)
    }

    @Test
    fun stringAnnotation() {
        val folder = tempFolder.newFolder()
        val complication = compilationFactory.create(folder)
        val inputCode = SourceFile.kotlin(
            "Test.kt",
            """
                package com.testpackage

                annotation class StringAnnotation(val value: String)

                @StringAnnotation("sample-string")
                val value = 0
            """,
        )
        complication.sources = listOf(inputCode)
        val result = complication.compile()

        assertCompileSucceed(result)

        val generatedFile = folder.resolve(generated)
        assertFileExists(generatedFile)

        val expectedCode = """
            |import com.testpackage.StringAnnotation
            |
            |public val annotation0: StringAnnotation = StringAnnotation(value = "sample-string")
            |""".trimMargin()
        assertFileTextEquals(expectedCode, generatedFile)
    }

    @Test
    fun charAnnotation() {
        val folder = tempFolder.newFolder()
        val complication = compilationFactory.create(folder)
        val inputCode = SourceFile.kotlin(
            "Test.kt",
            """
                package com.testpackage

                annotation class CharAnnotation(val value: Char)

                @CharAnnotation('a')
                val value = 0
            """,
        )
        complication.sources = listOf(inputCode)
        val result = complication.compile()

        assertCompileSucceed(result)

        val generatedFile = folder.resolve(generated)
        assertFileExists(generatedFile)

        val expectedCode = """
            |import com.testpackage.CharAnnotation
            |
            |public val annotation0: CharAnnotation = CharAnnotation(value = 'a')
            |""".trimMargin()
        assertFileTextEquals(expectedCode, generatedFile)
    }

    @Test
    fun booleanAnnotation() {
        val folder = tempFolder.newFolder()
        val complication = compilationFactory.create(folder)
        val inputCode = SourceFile.kotlin(
            "Test.kt",
            """
                package com.testpackage

                annotation class BooleanAnnotation(val value: Boolean)

                @BooleanAnnotation(false)
                val value = 0
            """,
        )
        complication.sources = listOf(inputCode)
        val result = complication.compile()

        assertCompileSucceed(result)

        val generatedFile = folder.resolve(generated)
        assertFileExists(generatedFile)

        val expectedCode = """
            |import com.testpackage.BooleanAnnotation
            |
            |public val annotation0: BooleanAnnotation = BooleanAnnotation(value = false)
            |""".trimMargin()
        assertFileTextEquals(expectedCode, generatedFile)
    }

    @Test
    fun defaultAnnotation() {
        val folder = tempFolder.newFolder()
        val complication = compilationFactory.create(folder)
        val inputCode = SourceFile.kotlin(
            "Test.kt",
            """
                package com.testpackage

                annotation class DefaultAnnotation(val value: String = "default")

                @DefaultAnnotation
                @DefaultAnnotation("a")
                val value = 0
            """,
        )
        complication.sources = listOf(inputCode)
        val result = complication.compile()

        assertCompileSucceed(result)

        val generatedFile = folder.resolve(generated)
        assertFileExists(generatedFile)

        val expectedCode = """
            |import com.testpackage.DefaultAnnotation
            |
            |public val annotation0: DefaultAnnotation = DefaultAnnotation(value = "default")
            |
            |public val annotation1: DefaultAnnotation = DefaultAnnotation(value = "a")
            |""".trimMargin()
        assertFileTextEquals(expectedCode, generatedFile)
    }

    @Test
    fun multipleMemberAnnotation() {
        val folder = tempFolder.newFolder()
        val complication = compilationFactory.create(folder)
        val inputCode = SourceFile.kotlin(
            "Test.kt",
            """
                package com.testpackage

                annotation class MultipleMemberAnnotation(
                    val string: String,
                    val int: Int,
                )

                @MultipleMemberAnnotation("string", 100)
                val value = 0
            """,
        )
        complication.sources = listOf(inputCode)
        val result = complication.compile()

        assertCompileSucceed(result)

        val generatedFile = folder.resolve(generated)
        assertFileExists(generatedFile)

        val expectedCode = """
            |import com.testpackage.MultipleMemberAnnotation
            |
            |public val annotation0: MultipleMemberAnnotation = MultipleMemberAnnotation(string = "string", int =
            |    100)
            |""".trimMargin()
        assertFileTextEquals(expectedCode, generatedFile)
    }
}
