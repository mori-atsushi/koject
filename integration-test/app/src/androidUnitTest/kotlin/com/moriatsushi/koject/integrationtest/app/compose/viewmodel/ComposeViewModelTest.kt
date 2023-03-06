package com.moriatsushi.koject.integrationtest.app.compose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.compose.viewmodel.injectViewModel
import com.moriatsushi.koject.integrationtest.app.viewmodel.SampleViewModel
import com.moriatsushi.koject.start
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertSame
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComposeViewModelTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @After
    fun clear() {
        Koject.stop()
    }

    @Test
    fun successInject() {
        Koject.start()

        var viewModel: SampleViewModel? = null

        composeTestRule.setContent {
            viewModel = injectViewModel()
        }

        assertNotNull(viewModel)
    }

    @Test
    fun injectSameViewModelAfterHide() {
        Koject.start()

        var visible by mutableStateOf(true)
        val viewModels = mutableListOf<SampleViewModel>()

        composeTestRule.setContent {
            if (visible) {
                val viewModel = injectViewModel<SampleViewModel>()
                viewModels.add(viewModel)
            }
        }

        composeTestRule.waitForIdle()

        visible = false
        composeTestRule.waitForIdle()

        visible = true
        composeTestRule.waitForIdle()

        assertEquals(2, viewModels.size)
        assertSame(viewModels[0], viewModels[1])
    }
}
