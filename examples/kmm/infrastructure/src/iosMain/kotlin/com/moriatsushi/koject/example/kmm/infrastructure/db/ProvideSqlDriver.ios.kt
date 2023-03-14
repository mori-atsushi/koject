package com.moriatsushi.koject.example.kmm.infrastructure.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Singleton

@Singleton
@Provides
internal fun provideSqlDriver(): SqlDriver {
    return NativeSqliteDriver(Database.Schema, "todo.db")
}
