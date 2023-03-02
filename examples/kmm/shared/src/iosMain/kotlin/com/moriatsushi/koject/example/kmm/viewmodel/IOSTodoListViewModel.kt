package com.moriatsushi.koject.example.kmm.viewmodel

import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.example.kmm.model.TodoTask
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Provides
class IOSTodoListViewModel(
    private val common: TodoListViewModel,
) {
    private val job = SupervisorJob()
    private val coroutineScope =
        CoroutineScope(job + Dispatchers.Main.immediate)

    fun observeTasks(f: (List<TodoTask>) -> Unit) {
        common.tasks
            .onEach { f(it) }
            .launchIn(coroutineScope)
    }

    fun addTask(title: String) = common.addTask(title)

    fun onCleared() {
        job.cancel()
    }
}
