package com.moriatsushi.koject.example.kmm.viewmodel

import com.moriatsushi.koject.Binds
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.example.kmm.model.TodoList
import com.moriatsushi.koject.example.kmm.model.TodoTask
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@Binds
@Provides
internal class TodoListViewModelImpl : TodoListViewModel {
    private val _tasks = MutableStateFlow(TodoList.empty)
    override val list: StateFlow<TodoList> = _tasks.asStateFlow()

    override fun addTask(title: String) {
        val newTask = TodoTask.new(title)
        _tasks.update { list ->
            list.added(newTask)
        }
    }

    override fun changeComplete(task: TodoTask, isCompleted: Boolean) {
        _tasks.update { list ->
            list.changeCompleted(task, isCompleted)
        }
    }
}
