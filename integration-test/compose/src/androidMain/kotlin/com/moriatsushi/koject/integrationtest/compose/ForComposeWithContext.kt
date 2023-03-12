package com.moriatsushi.koject.integrationtest.compose

import android.content.Context
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.compose.ComposeComponent
import com.moriatsushi.koject.compose.ComposeContext

@ComposeComponent
@Provides
class ForComposeWithContext(
    val applicationContext: Context,
    @ComposeContext
    val composeContext: Context,
)
