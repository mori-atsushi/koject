package com.moriatsushi.koject.example.kmm.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moriatsushi.koject.example.kmm.data.model.TodoList
import com.moriatsushi.koject.example.kmm.data.model.TodoTask

@Composable
fun TodoList(
    list: TodoList,
    onChangeCompleted: (TodoTask, Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp),
    ) {
        items(
            items = list.value,
            key = { it.id },
        ) { task ->
            val updatedTask by rememberUpdatedState(task)
            TodoTask(
                task = updatedTask,
                onChangeCompleted = {
                    onChangeCompleted(updatedTask, it)
                },
            )
        }
    }
}
