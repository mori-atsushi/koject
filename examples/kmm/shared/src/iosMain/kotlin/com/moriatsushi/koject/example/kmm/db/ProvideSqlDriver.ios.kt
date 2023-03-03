package com.moriatsushi.koject.example.kmm.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.moriatsushi.koject.Provides

@Provides
internal fun provideSqlDriver(): SqlDriver {
    return NativeSqliteDriver(Database.Schema, "todo.db")
}
