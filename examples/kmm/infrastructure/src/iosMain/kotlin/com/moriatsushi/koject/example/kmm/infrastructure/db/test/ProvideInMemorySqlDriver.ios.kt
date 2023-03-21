package com.moriatsushi.koject.example.kmm.infrastructure.db.test

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.inMemoryDriver
import com.moriatsushi.koject.Singleton
import com.moriatsushi.koject.example.kmm.infrastructure.db.Database
import com.moriatsushi.koject.test.TestProvides

@Singleton
@TestProvides
fun provideInMemorySqlDriver(): SqlDriver {
    return inMemoryDriver(Database.Schema)
}
