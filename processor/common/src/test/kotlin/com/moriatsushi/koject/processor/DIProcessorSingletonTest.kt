package com.moriatsushi.koject.processor

import com.moriatsushi.koject.processor.assert.assertCompileSucceed
import com.moriatsushi.koject.processor.assert.assertFileExists
import com.moriatsushi.koject.processor.compiletesting.KotlinCompilationFactory
import com.tschuchort.compiletesting.SourceFile
import java.io.File
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class DIProcessorSingletonTest {
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

        val expectedSingletonClass1FactoryFile =
            folder.resolve(expectedSingletonClass1FactoryFilePath)
        assertFileExists(expectedSingletonClass1FactoryFile)

        val expectedSingletonClass2FactoryFile =
            folder.resolve(expectedSingletonClass2FactoryFilePath)
        assertFileExists(expectedSingletonClass2FactoryFile)

        val expectedSingletonInterfaceFactoryFile =
            folder.resolve(expectedSingletonInterfaceFactoryFilePath)
        assertFileExists(expectedSingletonInterfaceFactoryFile)

        val expectedSingletonHolderFactoryFile =
            folder.resolve(expectedSingletonHolderFactoryFilePath)
        assertFileExists(expectedSingletonHolderFactoryFile)

        val expectedContainerFile = folder.resolve(expectedContainerFilePath)
        assertFileExists(expectedContainerFile)
    }

    private val inputCode = SourceFile.kotlin(
        "Test.kt",
        """
                package com.testpackage

                import com.moriatsushi.koject.Provides
                import com.moriatsushi.koject.Singleton

                @Singleton
                @Provides
                class SingletonClass1

                @Singleton
                @Provides
                class SingletonClass2(
                    private val class1: SingletonClass1
                )

                interface SingletonInterface
                
                @Provides
                @Singleton
                fun provideSingletonInterface(): SingletonInterface {
                    return object : SingletonInterface {}
                }

                @Provides
                class SingletonHolderClass(
                    val singletonClass: SingletonClass2,
                    val singletonInterface: SingletonInterface,
                )
            """,
    )

    private val expectedSingletonClass1FactoryFilePath =
        "ksp/sources/kotlin/com/moriatsushi/koject/generated/factory/" +
            "_com_testpackage_SingletonClass1_Factory.kt"

    private val expectedSingletonClass2FactoryFilePath =
        "ksp/sources/kotlin/com/moriatsushi/koject/generated/factory/" +
            "_com_testpackage_SingletonClass2_Factory.kt"

    private val expectedSingletonInterfaceFactoryFilePath =
        "ksp/sources/kotlin/com/moriatsushi/koject/generated/factory/" +
            "_com_testpackage_SingletonInterface_Factory.kt"

    private val expectedSingletonHolderFactoryFilePath =
        "ksp/sources/kotlin/com/moriatsushi/koject/generated/factory/" +
            "_com_testpackage_SingletonHolderClass_Factory.kt"

    private val expectedContainerFilePath =
        "ksp/sources/kotlin/com/moriatsushi/koject/generated/" +
            "_AppContainer.kt"
}
