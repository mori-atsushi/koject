package com.moriatsushi.koject.example.kmm.infrastructure.repository

import com.moriatsushi.koject.Binds
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Singleton
import com.moriatsushi.koject.example.kmm.data.model.TodoList
import com.moriatsushi.koject.example.kmm.data.model.TodoTask
import com.moriatsushi.koject.example.kmm.data.repository.TodoRepository
import com.moriatsushi.koject.example.kmm.infrastructure.db.TodoDao

@Binds
@Singleton
@Provides
internal class TodoRepositoryImpl(
    private val todoDao: TodoDao,
) : TodoRepository {
    override suspend fun fetchTodoList(): TodoList {
        return todoDao.fetchAll().toModel()
    }

    override suspend fun save(task: TodoTask) {
        todoDao.insertTodoTask(
            task.id,
            task.title,
            task.isCompleted,
        )
    }

    override suspend fun update(task: TodoTask) {
        todoDao.updateTodoTask(
            task.id,
            task.title,
            task.isCompleted,
        )
    }
}
