package com.moriatsushi.koject.example.kmm.viewmodel

import com.moriatsushi.koject.example.kmm.model.TodoList
import com.moriatsushi.koject.example.kmm.model.TodoTask
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

interface TodoListViewModel {
    val coroutineScope: CoroutineScope
    val list: StateFlow<TodoList>

    fun addTask(title: String)
    fun changeComplete(task: TodoTask, isCompleted: Boolean)
    fun onCleared()
}
