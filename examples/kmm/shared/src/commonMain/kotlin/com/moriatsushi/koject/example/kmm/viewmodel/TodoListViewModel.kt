package com.moriatsushi.koject.example.kmm.viewmodel

import com.moriatsushi.koject.example.kmm.model.TodoTask
import kotlinx.coroutines.flow.StateFlow


interface TodoListViewModel {
    val tasks: StateFlow<List<TodoTask>>

    fun addTask(title: String)
}
