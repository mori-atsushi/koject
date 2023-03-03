package com.moriatsushi.koject.example.kmm.repository

import com.moriatsushi.koject.example.kmm.db.TodoTask as TodoTaskEntity
import com.moriatsushi.koject.example.kmm.model.TodoList
import com.moriatsushi.koject.example.kmm.model.TodoTask

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
