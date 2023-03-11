package com.moriatsushi.koject.integrationtest.compose

import androidx.compose.runtime.Composable
import org.junit.rules.TestRule

expect fun createComposeRule(): ComposeRule

expect class ComposeRule : TestRule {
    fun setContent(composable: @Composable () -> Unit)
    fun waitForIdle()
}
