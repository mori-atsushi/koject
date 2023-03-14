package com.moriatsushi.koject.example.kmm.ui.viewmodel

import com.moriatsushi.koject.example.kmm.data.model.TodoList
import com.moriatsushi.koject.example.kmm.data.model.TodoTask
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

interface TodoListViewModel {
    val coroutineScope: CoroutineScope
    val list: StateFlow<TodoList>

    fun addTask(title: String)
    fun changeComplete(task: TodoTask, isCompleted: Boolean)
    fun onCleared()
}
