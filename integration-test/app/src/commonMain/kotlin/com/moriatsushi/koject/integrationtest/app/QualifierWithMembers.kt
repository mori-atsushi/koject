package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Qualifier
import kotlin.reflect.KClass

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class StringQualifier(val value: String = "default")

@StringQualifier()
@Provides
fun provideStringQualifierDefault(): String {
    return "StringQualifier-default"
}

@StringQualifier("id1")
@Provides
fun provideStringQualifier1(): String {
    return "StringQualifier-id1"
}

@StringQualifier("id2")
@Provides
fun provideStringQualifier2(): String {
    return "StringQualifier-id2"
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class EnumQualifier(val enum: QualifierEnum)

enum class QualifierEnum {
    ID1,
    ID2,
}

@EnumQualifier(QualifierEnum.ID1)
@Provides
fun provideEnumQualifier1(): String {
    return "EnumQualifier-id1"
}

@EnumQualifier(QualifierEnum.ID2)
@Provides
fun provideEnumQualifier2(): String {
    return "EnumQualifier-id2"
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ClassQualifier(val kClass: KClass<*>)

@ClassQualifier(String::class)
@Provides
fun provideClassQualifier1(): String {
    return "ClassQualifier-string"
}

@ClassQualifier(Int::class)
@Provides
fun provideClassQualifier2(): String {
    return "ClassQualifier-int"
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MultipleMemberQualifier(
    val value: String = "default",
    val kClass: KClass<*>,
)

@MultipleMemberQualifier("id1", String::class)
@Provides
fun provideMultipleMemberQualifier1(): String {
    return "MultipleMemberQualifier-id1-string"
}

@MultipleMemberQualifier(kClass = Int::class)
@Provides
fun provideMultipleMemberQualifier2(): String {
    return "MultipleMemberQualifier-default-int"
}

@Provides
class QualifierWithMembersHolder(
    @StringQualifier("id1")
    val stringQualifier1: String,
    @StringQualifier("id2")
    val stringQualifier2: String,
    @StringQualifier()
    val stringQualifierDefault: String,
    @EnumQualifier(QualifierEnum.ID1)
    val enumQualifier1: String,
    @EnumQualifier(QualifierEnum.ID2)
    val enumQualifier2: String,
    @ClassQualifier(String::class)
    val classQualifier1: String,
    @ClassQualifier(Int::class)
    val classQualifier2: String,
    @MultipleMemberQualifier("id1", String::class)
    val multipleMemberQualifier1: String,
    @MultipleMemberQualifier(kClass = Int::class)
    val multipleMemberQualifier2: String,
)
