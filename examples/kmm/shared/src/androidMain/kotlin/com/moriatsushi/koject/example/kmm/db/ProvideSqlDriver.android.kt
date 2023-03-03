package com.moriatsushi.koject.example.kmm.db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.moriatsushi.koject.Provides

@Provides
internal fun provideSqlDriver(
    context: Context,
): SqlDriver {
    return AndroidSqliteDriver(Database.Schema, context, "todo.db")
}
