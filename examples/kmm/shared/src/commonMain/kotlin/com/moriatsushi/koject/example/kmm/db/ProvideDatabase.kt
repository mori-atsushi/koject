package com.moriatsushi.koject.example.kmm.db

import app.cash.sqldelight.db.SqlDriver
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Singleton

@Singleton
@Provides
internal fun provideDatabase(
    driver: SqlDriver,
): Database {
    return Database(driver)
}
