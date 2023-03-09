package com.moriatsushi.koject.integrationtest.app.compose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.core.os.bundleOf
import androidx.lifecycle.DEFAULT_ARGS_KEY
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.compose.viewmodel.injectViewModel
import com.moriatsushi.koject.integrationtest.app.viewmodel.QualifierViewModel
import com.moriatsushi.koject.integrationtest.app.viewmodel.SampleViewModel
import com.moriatsushi.koject.integrationtest.app.viewmodel.SavedStateHandleViewModel
import com.moriatsushi.koject.integrationtest.app.viewmodel.ViewModelQualifier
import com.moriatsushi.koject.start
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNotSame
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
    fun injectSameViewModel() {
        Koject.start()

        var viewModel1: SampleViewModel? = null
        var viewModel2: SampleViewModel? = null

        composeTestRule.setContent {
            viewModel1 = injectViewModel()
            viewModel2 = injectViewModel()
        }

        assertSame(viewModel1, viewModel2)
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

    @Test
    fun injectDifferentViewModel_byKey() {
        Koject.start()

        var viewModel1: SampleViewModel? = null
        var viewModel2: SampleViewModel? = null

        composeTestRule.setContent {
            viewModel1 = injectViewModel(key = "1")
            viewModel2 = injectViewModel(key = "2")
        }

        assertNotSame(viewModel1, viewModel2)
    }

    @Test
    fun injectQualifierViewModel() {
        Koject.start()

        var viewModel: QualifierViewModel? = null
        composeTestRule.setContent {
            viewModel = injectViewModel(ViewModelQualifier())
        }
        assertNotNull(viewModel)
        assertEquals("QualifierViewModel", viewModel!!.name)
    }

    @Test
    fun injectSavedStateHandleViewModel() {
        Koject.start()

        var viewModel: SavedStateHandleViewModel? = null
        composeTestRule.setContent {
            viewModel = injectViewModel()
        }
        assertNotNull(viewModel)
    }

    @Test
    fun injectSavedStateHandleViewModelWithDefaultArgs() {
        Koject.start()

        var viewModel: SavedStateHandleViewModel? = null
        composeTestRule.setContent {
            val viewModelStoreOwner = LocalViewModelStoreOwner.current!!
            val extras = remember {
                MutableCreationExtras(
                    (viewModelStoreOwner as HasDefaultViewModelProviderFactory)
                        .defaultViewModelCreationExtras,
                ).apply {
                    this[DEFAULT_ARGS_KEY] = bundleOf("test-key" to "test-value")
                }
            }
            viewModel = injectViewModel(
                viewModelStoreOwner = viewModelStoreOwner,
                extras = extras,
            )
        }
        assertNotNull(viewModel)
        assertEquals("test-value", viewModel!!.savedStateHandle["test-key"])
    }
}
