package com.moriatsushi.koject.example.kmm.db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.moriatsushi.koject.Dynamic
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Singleton

@OptIn(ExperimentalKojectApi::class)
@Singleton
@Provides
internal fun provideSqlDriver(
    @Dynamic
    context: Context,
): SqlDriver {
    return AndroidSqliteDriver(Database.Schema, context, "todo.db")
}
