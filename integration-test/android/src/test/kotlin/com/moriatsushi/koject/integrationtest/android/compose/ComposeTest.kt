package com.moriatsushi.koject.integrationtest.android.compose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.compose.rememberInject
import com.moriatsushi.koject.integrationtest.android.NormalClass
import com.moriatsushi.koject.integrationtest.android.runTest
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertSame
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComposeTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun successInject() = Koject.runTest {
        var value: NormalClass? = null

        composeTestRule.setContent {
            value = rememberInject()
        }

        assertNotNull(value)
    }

    @Test
    fun rememberValue_whileRecompose() = Koject.runTest {
        var count by mutableStateOf(0)
        val values = mutableListOf<NormalClass>()

        composeTestRule.setContent {
            println(count)
            val value = rememberInject<NormalClass>()
            values.add(value)
        }

        composeTestRule.waitForIdle()

        count = 1 // recompose
        composeTestRule.waitForIdle()

        assertEquals(2, values.size)
        assertSame(values[0], values[1])
    }
}
