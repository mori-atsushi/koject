package com.moriatsushi.koject.example.kmm.ui.viewmodel

import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.example.kmm.data.model.TodoList
import com.moriatsushi.koject.example.kmm.data.model.TodoTask
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Provides
class IOSTodoListViewModel(
    private val common: TodoListViewModel,
) {
    fun observeTasks(f: (TodoList) -> Unit) {
        common.list
            .onEach { f(it) }
            .launchIn(common.coroutineScope)
    }

    fun addTask(title: String) =
        common.addTask(title)

    fun changeComplete(task: TodoTask, isCompleted: Boolean) =
        common.changeComplete(task, isCompleted)

    fun onCleared() {
        common.onCleared()
    }
}
