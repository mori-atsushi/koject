package com.moriatsushi.koject.example.kmm.di

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.moriatsushi.koject.example.kmm.infrastructure.db.Database
import com.moriatsushi.koject.test.TestProvides

@TestProvides
fun createInMemorySqlDriver(
    context: Context,
): SqlDriver {
    return AndroidSqliteDriver(
        schema = Database.Schema,
        context = context,
        name = null,
    )
}
