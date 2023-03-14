package com.moriatsushi.koject.example.kmm.data.repository

import com.moriatsushi.koject.example.kmm.data.model.TodoList
import com.moriatsushi.koject.example.kmm.data.model.TodoTask

interface TodoRepository {
    suspend fun fetchTodoList(): TodoList

    suspend fun save(task: TodoTask)

    suspend fun update(task: TodoTask)
}
