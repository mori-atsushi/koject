package com.moriatsushi.koject.integrationtest.compose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.compose.rememberInject
import com.moriatsushi.koject.integrationtest.compose.junit.RunWith
import com.moriatsushi.koject.integrationtest.compose.junit.UITestRunner
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlinx.coroutines.isActive
import org.junit.Rule
import org.junit.Test

@RunWith(UITestRunner::class)
class ComposeCoroutineScopeTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun successInject() = Koject.runTest {
        var value: ForComposeWithCoroutineScope? = null

        composeTestRule.setContent {
            value = rememberInject()
        }

        assertNotNull(value)
    }

    @Test
    fun cancelScope_whenHidden() = Koject.runTest {
        var visible by mutableStateOf(true)
        var value: ForComposeWithCoroutineScope? = null

        composeTestRule.setContent {
            if (visible) {
                value = rememberInject()
            }
        }

        assertTrue(value!!.scope.isActive)

        visible = false
        composeTestRule.waitForIdle()

        assertFalse(value!!.scope.isActive)
    }
}
