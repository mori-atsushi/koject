package com.moriatsushi.koject.integrationtest.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import org.junit.rules.TestRule

actual fun createComposeRule(): ComposeRule {
    val rule = androidx.compose.ui.test.junit4.createComposeRule()
    return ComposeRule(rule)
}

actual class ComposeRule(
    private val rule: ComposeContentTestRule,
) : TestRule by rule {
    actual fun setContent(composable: @Composable () -> Unit) {
        rule.setContent(composable)
    }

    actual fun waitForIdle() {
        rule.waitForIdle()
    }
}
