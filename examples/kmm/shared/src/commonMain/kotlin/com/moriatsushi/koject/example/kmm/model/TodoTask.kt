package com.moriatsushi.koject.example.kmm.model

data class TodoTask(
    val title: String,
    val isCompleted: Boolean,
) {
    companion object {
        fun new(title: String): TodoTask {
            return TodoTask(
                title = title,
                isCompleted = false,
            )
        }
    }
}
