package com.moriatsushi.koject.example.kmm.repository

import com.moriatsushi.koject.Binds
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.example.kmm.db.TodoDao
import com.moriatsushi.koject.example.kmm.model.TodoList
import com.moriatsushi.koject.example.kmm.model.TodoTask

@Binds
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
            if (task.isCompleted) 1L else 0,
        )
    }
}
