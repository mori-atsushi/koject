package com.moriatsushi.koject.example.kmm

import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UITest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun addTask() {
        val text = "Sample TODO Task"
        composeTestRule.onNode(hasSetTextAction())
            .performTextInput(text)
        composeTestRule.onNodeWithText("Add")
            .performClick()
        composeTestRule.onNodeWithText(text)
            .assertExists()
    }
}
