package com.moriatsushi.koject.example.kmm.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.moriatsushi.koject.Singleton
import com.moriatsushi.koject.example.kmm.infrastructure.db.Database
import com.moriatsushi.koject.test.TestProvides

@Singleton
@TestProvides
fun provideInMemorySqlDriver(): SqlDriver {
    return JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY).apply {
        Database.Schema.create(this)
    }
}
