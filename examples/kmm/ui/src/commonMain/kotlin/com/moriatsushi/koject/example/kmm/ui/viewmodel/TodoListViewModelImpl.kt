package com.moriatsushi.koject.example.kmm.ui.viewmodel

import com.moriatsushi.koject.Binds
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.example.kmm.data.di.Dispatcher
import com.moriatsushi.koject.example.kmm.data.di.Dispatchers.Main
import com.moriatsushi.koject.example.kmm.data.model.TodoList
import com.moriatsushi.koject.example.kmm.data.model.TodoTask
import com.moriatsushi.koject.example.kmm.data.repository.TodoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@Binds
@Provides
internal class TodoListViewModelImpl(
    private val todoRepository: TodoRepository,
    @Dispatcher(Main)
    private val dispatcher: CoroutineDispatcher,
) : TodoListViewModel {
    private val job = SupervisorJob()

    override val coroutineScope: CoroutineScope =
        CoroutineScope(job + dispatcher)

    private val _tasks = MutableStateFlow(TodoList.empty)
    override val list: StateFlow<TodoList> = _tasks.asStateFlow()

    init {
        coroutineScope.launch {
            _tasks.value = todoRepository.fetchTodoList()
        }
    }

    override fun addTask(title: String) {
        val newTask = TodoTask.new(title)
        _tasks.update { list ->
            list.added(newTask)
        }
        coroutineScope.launch {
            todoRepository.save(newTask)
        }
    }

    override fun changeComplete(task: TodoTask, isCompleted: Boolean) {
        val newTask = task.updatedCompleted(isCompleted)
        _tasks.update { list ->
            list.update(newTask)
        }
        coroutineScope.launch {
            todoRepository.update(newTask)
        }
    }

    override fun onCleared() {
        job.cancel()
    }
}
