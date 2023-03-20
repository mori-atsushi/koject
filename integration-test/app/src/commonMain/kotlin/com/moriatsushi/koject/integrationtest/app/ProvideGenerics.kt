package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Provides

@Provides
fun provideStringList(
    value: String,
): List<String> {
    return listOf(value)
}

@Provides
fun provideIntList(
    value: Int,
): List<Int> {
    return listOf(value)
}

@Provides
fun provideStarList(
    string: String,
    number: Int,
): List<*> {
    return listOf(string, number)
}

@Provides
fun provideMap(
    list: List<String>,
    value: String,
): Map<String, String> {
    return list.associateWith { value }
}
