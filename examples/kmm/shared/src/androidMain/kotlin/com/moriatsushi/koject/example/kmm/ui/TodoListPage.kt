package com.moriatsushi.koject.example.kmm.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.moriatsushi.koject.example.kmm.model.TodoList
import com.moriatsushi.koject.example.kmm.model.TodoTask
import com.moriatsushi.koject.example.kmm.ui.component.MyTopAppBar
import com.moriatsushi.koject.example.kmm.viewmodel.AndroidTodoListViewModel
import com.moriatsushi.koject.example.kmm.viewmodel.TodoListViewModel
import com.moriatsushi.koject.inject

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TodoListPage(
    modifier: Modifier = Modifier,
    viewModel: TodoListViewModel = viewModel {
        inject<AndroidTodoListViewModel>()
    },
) {
    val softwareKeyboardController = LocalSoftwareKeyboardController.current
    val list by viewModel.list.collectAsState()
    var editingTitle by rememberSaveable {
        mutableStateOf("")
    }

    TodoListPage(
        modifier = modifier,
        list = list,
        editingTitle = editingTitle,
        onChangeEditingTitle = { editingTitle = it },
        onClickAddButton = {
            viewModel.addTask(editingTitle)
            editingTitle = ""
            softwareKeyboardController?.hide()
        },
        onChangeCompleted = viewModel::changeComplete,
    )
}

@Composable
fun TodoListPage(
    modifier: Modifier = Modifier,
    list: TodoList,
    editingTitle: String,
    onChangeEditingTitle: (String) -> Unit,
    onClickAddButton: () -> Unit,
    onChangeCompleted: (TodoTask, Boolean) -> Unit,
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.windowInsetsPadding(
                WindowInsets.safeDrawing.only(WindowInsetsSides.Bottom),
            ),
        ) {
            MyTopAppBar(
                title = {
                    Text(text = "Koject TODO")
                },
            )
            TodoList(
                modifier = Modifier
                    .weight(1F)
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal),
                    ),
                list = list,
                onChangeCompleted = onChangeCompleted,
            )
            TodoTaskField(
                modifier = Modifier
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal),
                    ),
                title = editingTitle,
                onChangeTitle = onChangeEditingTitle,
                onClickAddButton = onClickAddButton,
            )
        }
    }
}
