package com.moriatsushi.koject.integrationtest.app.compose.viewmodel

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.compose.viewmodel.injectViewModel
import com.moriatsushi.koject.integrationtest.app.viewmodel.SavedStateHandleViewModel
import com.moriatsushi.koject.start
import kotlin.test.assertNotNull
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SavedStateHandleViewModelTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @After
    fun clear() {
        Koject.stop()
    }

    @Test
    fun successInject() {
        Koject.start()

        var viewModel: SavedStateHandleViewModel? = null

        composeTestRule.setContent {
            viewModel = injectViewModel()
        }
        assertNotNull(viewModel)
    }
}
