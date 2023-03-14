package com.moriatsushi.koject.example.kmm.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moriatsushi.koject.example.kmm.data.model.TodoTask

@Composable
fun TodoTask(
    task: TodoTask,
    onChangeCompleted: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val isCompleted by rememberUpdatedState(task.isCompleted)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onChangeCompleted(!isCompleted) }
            .padding(vertical = 4.dp)
            .padding(start = 8.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = isCompleted,
            onCheckedChange = onChangeCompleted,
            colors = checkboxColors,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = task.title,
            fontSize = 16.sp,
        )
    }
}

private val checkboxColors
    @Composable
    get() = CheckboxDefaults.colors(
        checkedColor = MaterialTheme.colors.primary,
    )
