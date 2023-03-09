package com.moriatsushi.koject.integrationtest.app.viewmodel

import androidx.activity.ComponentActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.DEFAULT_ARGS_KEY
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.android.activity.injectViewModels
import com.moriatsushi.koject.error.NotProvidedException
import com.moriatsushi.koject.start
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertIs
import kotlin.test.assertNotNull
import kotlin.test.assertSame
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ActivityViewModelTest {
    @After
    fun clear() {
        Koject.stop()
    }

    @Test
    fun injectViewModel() {
        Koject.start()

        val scenario = launchActivity<ComponentActivity>()
        scenario.onActivity {
            val viewModel: SampleViewModel by it.injectViewModels()
            assertIs<SampleViewModel>(viewModel)
        }
    }

    @Test
    fun injectSameViewModelAfterRecreate() {
        Koject.start()

        val scenario = launchActivity<ComponentActivity>()
        lateinit var viewModel1: SampleViewModel
        lateinit var viewModel2: SampleViewModel

        scenario.onActivity {
            val viewModel: SampleViewModel by it.injectViewModels()
            viewModel1 = viewModel
        }

        scenario.recreate()

        scenario.onActivity {
            val viewModel: SampleViewModel by it.injectViewModels()
            viewModel2 = viewModel
        }

        assertSame(viewModel1, viewModel2)
    }

    @Test
    fun failedInjectViewModel_whenNotProvided() {
        Koject.start()

        val scenario = launchActivity<ComponentActivity>()
        scenario.onActivity {
            val viewModel: NotProvidedViewModel by it.injectViewModels()
            assertFailsWith<NotProvidedException> {
                print(viewModel)
            }
        }
    }

    @Test
    fun injectQualifierViewModel() {
        Koject.start()

        val scenario = launchActivity<ComponentActivity>()
        var viewModel: QualifierViewModel? = null
        scenario.onActivity {
            val value by it.injectViewModels<QualifierViewModel>(ViewModelQualifier())
            viewModel = value
        }
        assertNotNull(viewModel)
        assertEquals("QualifierViewModel", viewModel!!.name)
    }

    @Test
    fun injectSavedStateHandleViewModel() {
        Koject.start()

        val scenario = launchActivity<ComponentActivity>()
        var viewModel: SavedStateHandleViewModel? = null
        scenario.onActivity {
            val value by it.injectViewModels<SavedStateHandleViewModel>()
            viewModel = value
        }
        assertNotNull(viewModel)
    }

    @Test
    fun injectSavedStateHandleViewModelWithDefaultArgs() {
        Koject.start()

        val scenario = launchActivity<ComponentActivity>()
        var viewModel: SavedStateHandleViewModel? = null
        scenario.onActivity {
            val extras = MutableCreationExtras(it.defaultViewModelCreationExtras)
            extras[DEFAULT_ARGS_KEY] = bundleOf("test-key" to "test-value")
            val value by it.injectViewModels<SavedStateHandleViewModel>(
                extrasProducer = { extras },
            )
            viewModel = value
        }
        assertNotNull(viewModel)
        assertEquals("test-value", viewModel!!.savedStateHandle["test-key"])
    }
}
