package com.moriatsushi.koject.example.kmm.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MyTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier
            .background(MaterialTheme.colors.primarySurface)
            .windowInsetsPadding(topAppBarWindowInsets),
        elevation = 0.dp,
        title = title,
    )
}

private val topAppBarWindowInsets
    @Composable
    get() = WindowInsets.safeDrawing.only(
        WindowInsetsSides.Top + WindowInsetsSides.Horizontal,
    )
