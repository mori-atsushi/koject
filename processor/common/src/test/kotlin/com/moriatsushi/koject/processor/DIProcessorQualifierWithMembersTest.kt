package com.moriatsushi.koject.processor

import com.moriatsushi.koject.internal.StringIdentifier
import com.moriatsushi.koject.processor.assert.assertCompileSucceed
import com.moriatsushi.koject.processor.assert.assertFileExists
import com.moriatsushi.koject.processor.compiletesting.KotlinCompilationFactory
import com.moriatsushi.koject.processor.symbol.asCodeName
import com.tschuchort.compiletesting.SourceFile
import java.io.File
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class DIProcessorQualifierWithMembersTest {
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

        val expectedID1FactoryFile = folder.resolve(expectedID1FactoryFilePath)
        assertFileExists(expectedID1FactoryFile)

        val expectedClassFactoryFile = folder.resolve(expectedClassFactoryFilePath)
        assertFileExists(expectedClassFactoryFile)

        val expectedFunctionFactoryFile = folder.resolve(expectedFunctionFactoryFilePath)
        assertFileExists(expectedFunctionFactoryFile)
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
                fun provideString1(): String {
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

    private val expectedID1Identifier = StringIdentifier(
        "kotlin.String",
        "com.testpackage.EnumQualifier(enum = com.testpackage.QualifierEnum.ID1)",
    )
    private val expectedID2Identifier = StringIdentifier(
        "kotlin.String",
        "com.testpackage.EnumQualifier(enum = com.testpackage.QualifierEnum.ID2)",
    )

    private val expectedID1FactoryFilePath =
        "ksp/sources/kotlin/com/moriatsushi/koject/generated/factory/" +
            "_${expectedID1Identifier.asCodeName()}_Factory.kt"

    private val expectedClassFactoryFilePath =
        "ksp/sources/kotlin/com/moriatsushi/koject/generated/factory/" +
            "_com_testpackage_SampleClass_Factory.kt"

    private val expectedFunctionIdentifier = StringIdentifier(
        "com.testpackage.SampleClass",
        "com.testpackage.EnumQualifier(enum = com.testpackage.QualifierEnum.ID1)",
    )
    private val expectedFunctionFactoryFilePath =
        "ksp/sources/kotlin/com/moriatsushi/koject/generated/factory/" +
            "_${expectedFunctionIdentifier.asCodeName()}_Factory.kt"
}
