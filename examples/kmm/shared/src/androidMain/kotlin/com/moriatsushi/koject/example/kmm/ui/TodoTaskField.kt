package com.moriatsushi.koject.example.kmm.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TodoTaskField(
    title: String,
    onChangeTitle: (String) -> Unit,
    onClickAddButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column {
        Divider()
        Row(
            modifier = modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextField(
                modifier = Modifier.weight(1f),
                value = title,
                onValueChange = onChangeTitle,
                placeholder = { Text(text = "Input title…") },
                colors = textFieldColors,
            )
            Button(
                modifier = Modifier.padding(end = 8.dp),
                onClick = onClickAddButton,
                shape = RoundedCornerShape(50),
                elevation = null,
            ) {
                Text(text = "Add")
            }
        }
    }
}

private val textFieldColors
    @Composable
    get() = TextFieldDefaults.textFieldColors(
        disabledTextColor = Color.Transparent,
        backgroundColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
    )
