package com.moriatsushi.koject.integrationtest.compose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.compose.rememberInject
import com.moriatsushi.koject.integrationtest.compose.junit.RunWith
import com.moriatsushi.koject.integrationtest.compose.junit.UITestRunner
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertSame
import org.junit.Rule
import org.junit.Test

@RunWith(UITestRunner::class)
class ComposeTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun successInject() = Koject.runTest {
        var value: CommonClass? = null

        composeTestRule.setContent {
            value = rememberInject()
        }

        assertNotNull(value)
    }

    @Test
    fun successInject_singleton() = Koject.runTest {
        var value1: CommonSingleton? = null
        var value2: CommonSingleton? = null

        composeTestRule.setContent {
            value1 = rememberInject()
            value2 = rememberInject()
        }

        assertSame(value1, value2)
    }

    @Test
    fun successInject_component() = Koject.runTest {
        var value: ForComposeClass? = null
        var holder: ForComposeHolder? = null

        composeTestRule.setContent {
            value = rememberInject()
            holder = rememberInject()
        }

        assertNotNull(value)
        assertNotNull(holder)
    }

    @Test
    fun rememberValue_whileRecompose() = Koject.runTest {
        var count by mutableStateOf(0)
        val values = mutableListOf<CommonClass>()

        composeTestRule.setContent {
            println(count)
            val value = rememberInject<CommonClass>()
            values.add(value)
        }

        composeTestRule.waitForIdle()

        count = 1 // recompose
        composeTestRule.waitForIdle()

        assertEquals(2, values.size)
        assertSame(values[0], values[1])
    }
}
