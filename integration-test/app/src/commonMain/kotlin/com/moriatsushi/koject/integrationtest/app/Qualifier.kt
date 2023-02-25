package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ID1

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ID2

@Provides
fun provideQualifierStringNotSet(): QualifierClass {
    return QualifierClass("not set")
}

@ID1
@Provides
fun provideQualifier1(): QualifierClass {
    return QualifierClass("id1")
}

@ID2
@Provides
fun provideQualifier2(): QualifierClass {
    return QualifierClass("id2")
}

@Provides
data class QualifierHolderClass(
    val notSet: QualifierClass,
    @ID1
    val id1: QualifierClass,
    @ID2
    val id2: QualifierClass,
)

class QualifierClass(
    val string: String,
)

@Provides
fun provideQualifierHolderInterface(
    notSet: QualifierClass,
    @ID1 id1: QualifierClass,
    @ID2 id2: QualifierClass,
): QualifierHolderInterface {
    return object : QualifierHolderInterface {
        override val notSet: QualifierClass = notSet
        override val id1: QualifierClass = id1
        override val id2: QualifierClass = id2
    }
}

interface QualifierHolderInterface {
    val notSet: QualifierClass
    val id1: QualifierClass
    val id2: QualifierClass
}
