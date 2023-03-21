package com.moriatsushi.koject.processor.symbol

import com.google.devtools.ksp.symbol.KSFile
import com.moriatsushi.koject.internal.Location
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.MemberName
import kotlin.test.assertEquals
import org.junit.Test

class ProviderDeclarationTest {
    @Test
    fun factoryName_byClass() {
        val subject = dummyProviderDeclaration(
            name = ProviderName.Class(
                className = ClassName("com.sample", "Sample"),
            ),
            identifier = TypedIdentifier(
                typeName = ClassName("com.sample", "Sample"),
                qualifier = null,
            ),
        )
        val actual = subject.factoryName
        val expected = "_com_sample_Sample_Factory"
        assertEquals(expected, actual)
    }

    @Test
    fun factoryName_binds() {
        val subject = dummyProviderDeclaration(
            name = ProviderName.Class(
                className = ClassName("com.sample", "SampleImpl"),
            ),
            identifier = TypedIdentifier(
                typeName = ClassName("com.sample", "Sample"),
                qualifier = null,
            ),
        )
        val actual = subject.factoryName
        val expected = "_com_sample_Sample__JfuxQzV2OMx7qtFr_Factory"
        assertEquals(expected, actual)
    }

    @Test
    fun factoryName_byFunction() {
        val subject = dummyProviderDeclaration(
            name = ProviderName.Function(
                objectName = null,
                functionName = MemberName("com.sample", "provideSample"),
            ),
            identifier = TypedIdentifier(
                typeName = ClassName("com.sample", "Sample"),
                qualifier = null,
            ),
        )
        val actual = subject.factoryName
        val expected = "_com_sample_Sample__oclo3hq1aCP1iFwg_Factory"
        assertEquals(expected, actual)
    }

    @Test
    fun factoryName_withQualifier() {
        val subject = dummyProviderDeclaration(
            name = ProviderName.Function(
                objectName = null,
                functionName = MemberName("com.sample", "provideSample"),
            ),
            identifier = TypedIdentifier(
                typeName = ClassName("com.sample", "Sample"),
                qualifier = QualifierAnnotation(
                    fullName = "Named(name)",
                    newInstanceCode = CodeBlock.of("Named(\"name\")"),
                ),
            ),
        )
        val actual = subject.factoryName
        val expected = "_com_sample_Sample__OnLWfPTsJZ7N9X06_Factory"
        assertEquals(expected, actual)
    }

    private fun dummyProviderDeclaration(
        name: ProviderName = ProviderName.Class(
            className = ClassName("com.sample", "Sample"),
        ),
        identifier: TypedIdentifier = TypedIdentifier(
            typeName = ClassName("com.sample", "Sample"),
            qualifier = null,
        ),
        component: ComponentName? = null,
        parameters: List<ProviderParameter>? = emptyList(),
        isSingleton: Boolean = false,
        forTest: Boolean = false,
        location: Location = Location("location"),
        containingFile: KSFile? = null,
    ): ProviderDeclaration = ProviderDeclaration(
        name = name,
        identifier = identifier,
        component = component,
        parameters = parameters,
        isSingleton = isSingleton,
        forTest = forTest,
        location = location,
        containingFile = containingFile,
    )
}
