package com.moriatsushi.koject.processor

import com.moriatsushi.koject.processor.assert.assertCompileSucceed
import com.moriatsushi.koject.processor.assert.assertExistsFile
import com.moriatsushi.koject.processor.compiletesting.KotlinCompilationFactory
import com.tschuchort.compiletesting.SourceFile
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class DIProcessorTest {
    @get:Rule
    val tempFolder: TemporaryFolder = TemporaryFolder()

    @Test
    fun content() {
        val folder = tempFolder.newFolder()
        val compilationFactory = KotlinCompilationFactory(folder)

        val kotlinSource = SourceFile.kotlin(
            "Test.kt",
            """
                package com.testpackage

                import com.moriatsushi.koject.Provides

                @Provides
                class SampleClass1

                @Provides
                class SampleClass2
            """,
        )

        val complication = compilationFactory.create(kotlinSource)
        val result = complication.compile()

        assertCompileSucceed(result)

        assertExistsFile(
            folder,
            "ksp/sources/kotlin/com/moriatsushi/koject/di/generated/factory/" +
                "_com_testpackage_SampleClass1_Factory.kt",
        )
        assertExistsFile(
            folder,
            "ksp/sources/kotlin/com/moriatsushi/koject/di/generated/factory/" +
                "_com_testpackage_SampleClass1_Factory.kt",
        )
    }
}
