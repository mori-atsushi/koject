package com.moriatsushi.koject.example.kmm.viewmodel

import com.moriatsushi.koject.Binds
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.example.kmm.model.TodoTask
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@Binds
@Provides
internal class TodoListViewModelImpl : TodoListViewModel {
    private val _tasks = MutableStateFlow(emptyList<TodoTask>())
    override val tasks: StateFlow<List<TodoTask>> = _tasks.asStateFlow()

    override fun addTask(title: String) {
        _tasks.update {
            listOf(TodoTask.new(title)) + it
        }
    }
}
