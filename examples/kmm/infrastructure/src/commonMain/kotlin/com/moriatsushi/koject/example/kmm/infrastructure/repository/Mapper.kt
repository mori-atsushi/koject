package com.moriatsushi.koject.example.kmm.infrastructure.repository

import com.moriatsushi.koject.example.kmm.data.model.TodoList
import com.moriatsushi.koject.example.kmm.data.model.TodoTask
import com.moriatsushi.koject.example.kmm.infrastructure.db.TodoTask as TodoTaskEntity

internal fun List<TodoTaskEntity>.toModel(): TodoList {
    val list = map {
        TodoTask(
            it.id,
            it.title,
            it.isCompleted,
        )
    }
    return TodoList(list)
}
