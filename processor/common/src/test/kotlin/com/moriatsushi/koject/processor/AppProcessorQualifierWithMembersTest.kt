package com.moriatsushi.koject.processor

import com.moriatsushi.koject.processor.assert.assertCompileSucceed
import com.moriatsushi.koject.processor.compiletesting.KotlinCompilationFactory
import com.tschuchort.compiletesting.SourceFile
import java.io.File
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class AppProcessorQualifierWithMembersTest {
    @get:Rule
    val tempFolder: TemporaryFolder = TemporaryFolder()

    private val compilationFactory = KotlinCompilationFactory()
    private lateinit var folder: File

    @Before
    fun setup() {
        folder = tempFolder.newFolder()
    }

    @Test
    fun compile() {
        val complication = compilationFactory.create(folder)
        complication.sources = listOf(inputCode)
        val result = complication.compile()

        assertCompileSucceed(result)
    }

    private val inputCode = SourceFile.kotlin(
        "Test.kt",
        """
                package com.testpackage

                import com.moriatsushi.koject.Provides
                import com.moriatsushi.koject.Qualifier

                @Qualifier
                @Retention(AnnotationRetention.BINARY)
                annotation class EnumQualifier(val enum: QualifierEnum)

                enum class QualifierEnum {
                    ID1,
                    ID2
                }

                @EnumQualifier(QualifierEnum.ID1)
                @Provides
                fun provideString1(): String {
                    return "id1"
                }

                @EnumQualifier(QualifierEnum.ID2)
                @Provides
                fun provideString2(): String {
                    return "id2"
                }

                @Provides
                class SampleClass(
                    @EnumQualifier(QualifierEnum.ID1)
                    private val string1: String,
                    @EnumQualifier(QualifierEnum.ID2)
                    private val string2: String,
                )

                @EnumQualifier(QualifierEnum.ID1)
                @Provides
                fun provideSampleClass(
                    @EnumQualifier(QualifierEnum.ID1) string1: String,
                    @EnumQualifier(QualifierEnum.ID2) string2: String,
                ): SampleClass {
                    return SampleClass(
                        string1 + "by-id1",
                        string2 + "by-id1",
                    )
                }
            """,
    )
}
