package com.moriatsushi.koject.example.kmm.model

data class TodoList(
    val value: List<TodoTask>,
) {
    companion object {
        val empty = TodoList(emptyList())
    }

    fun added(task: TodoTask): TodoList {
        return TodoList(listOf(task) + value)
    }

    fun update(task: TodoTask): TodoList {
        val newList = value.map {
            if (it.id == task.id) task else it
        }
        return TodoList(newList)
    }
}
