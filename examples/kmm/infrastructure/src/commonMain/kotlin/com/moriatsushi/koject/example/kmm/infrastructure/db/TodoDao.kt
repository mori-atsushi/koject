package com.moriatsushi.koject.example.kmm.infrastructure.db

internal interface TodoDao {
    suspend fun fetchAll(): List<TodoTask>

    suspend fun insertTodoTask(
        id: String,
        title: String,
        isCompleted: Boolean,
    )

    suspend fun updateTodoTask(
        id: String,
        title: String,
        isCompleted: Boolean,
    )
}
