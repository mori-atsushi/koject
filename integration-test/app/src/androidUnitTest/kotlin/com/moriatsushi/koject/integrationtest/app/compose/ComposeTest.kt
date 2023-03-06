package com.moriatsushi.koject.integrationtest.app.compose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.compose.rememberInject
import com.moriatsushi.koject.integrationtest.app.AppClass1
import com.moriatsushi.koject.start
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertSame
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComposeTest {
    @After
    fun clear() {
        Koject.stop()
    }

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun successInject() {
        Koject.start()
        var value: AppClass1? = null

        composeTestRule.setContent {
            value = rememberInject()
        }

        assertNotNull(value)
    }

    @Test
    fun rememberValue_whileRecompose() {
        Koject.start()
        var count by mutableStateOf(0)
        val values = mutableListOf<AppClass1>()

        composeTestRule.setContent {
            println(count)
            val value = rememberInject<AppClass1>()
            values.add(value)
        }

        composeTestRule.waitForIdle()

        count = 1 // recompose
        composeTestRule.waitForIdle()

        assertEquals(2, values.size)
        assertSame(values[0], values[1])
    }
}
