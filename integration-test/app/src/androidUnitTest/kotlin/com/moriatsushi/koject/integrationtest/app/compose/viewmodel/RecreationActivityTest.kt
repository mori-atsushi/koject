package com.moriatsushi.koject.integrationtest.app.compose.viewmodel

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.integrationtest.app.runTest
import com.moriatsushi.koject.integrationtest.app.viewmodel.SampleViewModel
import kotlin.test.assertSame
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecreationActivityTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun injectSameViewModelAfterRecreate() = Koject.runTest {
        val scenario = launchActivity<RecreationTestActivity>()
        var viewModel1: SampleViewModel? = null
        var viewModel2: SampleViewModel? = null

        scenario.onActivity {
            viewModel1 = it.viewModel
        }

        scenario.recreate()

        scenario.onActivity {
            viewModel2 = it.viewModel
        }

        assertSame(viewModel1, viewModel2)
    }
}
