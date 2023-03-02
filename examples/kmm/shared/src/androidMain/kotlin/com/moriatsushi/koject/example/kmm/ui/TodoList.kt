package com.moriatsushi.koject.example.kmm.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.moriatsushi.koject.example.kmm.model.TodoTask
import com.moriatsushi.koject.example.kmm.viewmodel.AndroidTodoListViewModel
import com.moriatsushi.koject.example.kmm.viewmodel.TodoListViewModel
import com.moriatsushi.koject.inject

@Composable
fun TodoList(
    modifier: Modifier = Modifier,
    viewModel: TodoListViewModel = viewModel {
        inject<AndroidTodoListViewModel>()
    },
) {
    val tasks by viewModel.tasks.collectAsState()
    var editingTitle by rememberSaveable {
        mutableStateOf("")
    }

    TodoList(
        modifier = modifier,
        tasks = tasks,
        editingTitle = editingTitle,
        onChangeEditingTitle = { editingTitle = it },
        onClickConfirmButton = {
            viewModel.addTask(editingTitle)
            editingTitle = ""
        },
    )
}

@Composable
fun TodoList(
    tasks: List<TodoTask>,
    editingTitle: String,
    onChangeEditingTitle: (String) -> Unit,
    onClickConfirmButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Row {
            TextField(
                modifier = Modifier.weight(1f),
                value = editingTitle,
                onValueChange = onChangeEditingTitle,
            )
            Button(onClick = onClickConfirmButton) {
                Text(text = "Confirm")
            }
        }
        LazyColumn(
            modifier = Modifier.weight(1F),
        ) {
            items(tasks) {
                Text(text = it.title)
            }
        }
    }
}
