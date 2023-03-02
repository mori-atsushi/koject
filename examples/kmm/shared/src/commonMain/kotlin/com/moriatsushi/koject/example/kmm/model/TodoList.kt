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

    fun changeCompleted(task: TodoTask, isCompleted: Boolean): TodoList {
        val newList = value.map {
            if (it == task) {
                task.updatedCompleted(isCompleted)
            } else {
                it
            }
        }
        return TodoList(newList)
    }
}
