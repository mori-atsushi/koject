package com.moriatsushi.koject.example.kmm.db

import app.cash.sqldelight.db.SqlDriver
import com.moriatsushi.koject.Provides

@Provides
internal fun provideDatabase(
    driver: SqlDriver,
): Database {
    return Database(driver)
}
