package com.moriatsushi.koject.processor

import com.moriatsushi.koject.processor.assert.assertCompileSucceed
import com.moriatsushi.koject.processor.assert.assertFileExists
import com.moriatsushi.koject.processor.compiletesting.KotlinCompilationFactory
import com.tschuchort.compiletesting.KotlinCompilation
import com.tschuchort.compiletesting.SourceFile
import java.io.File
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class LiProcessorCopyComponentExtrasTest {
    @get:Rule
    val tempFolder: TemporaryFolder = TemporaryFolder()

    @Test
    fun success_copyComponentExtras() {
        val folder = tempFolder.newFolder()
        val complication = createComplication(folder, "lib")
        complication.sources = listOf(generatedComponentExtrasCode)
        val result = complication.compile()

        assertCompileSucceed(result)

        val expectedCopiedFactoryFile = folder.resolve(expectedCopiedComponentExtrasFilePath)
        assertFileExists(expectedCopiedFactoryFile)
    }

    @Test
    fun success_copyCopiedFactory() {
        val folder = tempFolder.newFolder()
        val complication = createComplication(folder, "lib2")
        complication.sources = listOf(copiedCode)
        val result = complication.compile()

        assertCompileSucceed(result)

        val expectedCopiedFactoryFile = folder.resolve(expectedCopied2ComponentExtrasFilePath)
        assertFileExists(expectedCopiedFactoryFile)
    }

    @Test
    fun success_multipleCopiedFactory() {
        val folder = tempFolder.newFolder()
        val complication = createComplication(folder, "lib3")
        complication.sources = listOf(generatedComponentExtrasCode, copiedCode)
        val result = complication.compile()

        assertCompileSucceed(result)

        val expectedCopiedFactoryFile = folder.resolve(expectedCopied3ComponentExtrasFilePath)
        assertFileExists(expectedCopiedFactoryFile)
    }

    private fun createComplication(file: File, moduleName: String): KotlinCompilation {
        val provider = TestLibProcessorProvider(
            options = DIProcessorOptions(moduleName = moduleName),
        )
        val compilationFactory = KotlinCompilationFactory(listOf(provider))
        return compilationFactory.create(file)
    }

    private val generatedComponentExtrasCode = SourceFile.kotlin(
        "_com_lib_SampleComponentExtras_Holder.kt",
        """
                // Generated by Koject. Do not modify!
                package com.moriatsushi.koject.generated.component
                
                import com.moriatsushi.koject.`internal`.Identifier
                import com.moriatsushi.koject.`internal`.InternalKojectApi
                import com.moriatsushi.koject.internal.Location
                import com.moriatsushi.koject.`internal`.StringComponent
                import kotlin.Any
                import kotlin.reflect.KClass
                
                @InternalKojectApi
                @StringComponent("com.lib.SampleComponent")
                @Location("/sources/Test.kt:8")
                public class _com_lib_SampleComponentExtras_Holder(extras: Any) {
                    public companion object {
                        public val kClass: KClass<*> = TODO()
                    }
                }
            """,
    )

    private val copiedCode = SourceFile.kotlin(
        "_lib__com_lib_SampleClass_Factory.kt",
        """
                // Generated by Koject. Do not modify!
                package com.moriatsushi.koject.generated.component
                
                import com.moriatsushi.koject.`internal`.Copied
                import com.moriatsushi.koject.`internal`.Identifier
                import com.moriatsushi.koject.`internal`.InternalKojectApi
                import com.moriatsushi.koject.internal.Location
                import com.moriatsushi.koject.`internal`.StringComponent
                import kotlin.Any
                import kotlin.reflect.KClass
                
                @InternalKojectApi
                @StringComponent("com.lib.SampleComponent")
                @Location("/sources/Test.kt:8")
                @Copied(1)
                public class _lib__com_lib_SampleComponentExtras_Holder(extras: Any) {
                    public companion object {
                        public val kClass: KClass<*> = TODO()
                    }
                }
            """,
    )

    private val expectedCopiedComponentExtrasFilePath =
        "ksp/sources/kotlin/com/moriatsushi/koject/generated/component/" +
            "_lib__com_lib_SampleComponentExtras_Holder.kt"

    private val expectedCopied2ComponentExtrasFilePath =
        "ksp/sources/kotlin/com/moriatsushi/koject/generated/component/" +
            "_lib2__lib__com_lib_SampleComponentExtras_Holder.kt"

    private val expectedCopied3ComponentExtrasFilePath =
        "ksp/sources/kotlin/com/moriatsushi/koject/generated/component/" +
            "_lib3__com_lib_SampleComponentExtras_Holder.kt"
}