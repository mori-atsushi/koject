package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Provides

enum class SampleEnum {
    Item1,
    Item2,
}

@Provides
fun provideEnum(): SampleEnum {
    return SampleEnum.Item1
}

@Provides
class EnumHolder(
    val item: SampleEnum,
)
