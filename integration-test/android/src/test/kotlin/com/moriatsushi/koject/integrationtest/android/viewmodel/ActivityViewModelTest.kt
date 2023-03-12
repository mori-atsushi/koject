package com.moriatsushi.koject.integrationtest.android.viewmodel

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.DEFAULT_ARGS_KEY
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.android.activity.lazyViewModels
import com.moriatsushi.koject.error.NotProvidedException
import com.moriatsushi.koject.integrationtest.android.runTest
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertIs
import kotlin.test.assertNotNull
import kotlin.test.assertSame
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ActivityViewModelTest {
    @Test
    fun lazyViewModel() = Koject.runTest {
        val scenario = launchActivity<ComponentActivity>()
        scenario.onActivity {
            val viewModel: SampleViewModel by it.lazyViewModels()
            assertIs<SampleViewModel>(viewModel)
        }
    }

    @Test
    fun injectSameViewModelAfterRecreate() = Koject.runTest {
        val scenario = launchActivity<ComponentActivity>()
        lateinit var viewModel1: SampleViewModel
        lateinit var viewModel2: SampleViewModel

        scenario.onActivity {
            val viewModel: SampleViewModel by it.lazyViewModels()
            viewModel1 = viewModel
        }

        scenario.recreate()

        scenario.onActivity {
            val viewModel: SampleViewModel by it.lazyViewModels()
            viewModel2 = viewModel
        }

        assertSame(viewModel1, viewModel2)
    }

    @Test
    fun failedlazyViewModel_whenNotProvided() = Koject.runTest {
        val scenario = launchActivity<ComponentActivity>()
        scenario.onActivity {
            val viewModel: NotProvidedViewModel by it.lazyViewModels()
            assertFailsWith<NotProvidedException> {
                print(viewModel)
            }
        }
    }

    @Test
    fun injectQualifierViewModel() = Koject.runTest {
        val scenario = launchActivity<ComponentActivity>()
        var viewModel: QualifierViewModel? = null
        scenario.onActivity {
            val value by it.lazyViewModels<QualifierViewModel>(ViewModelQualifier())
            viewModel = value
        }
        assertNotNull(viewModel)
        assertEquals("QualifierViewModel", viewModel!!.name)
    }

    @Test
    fun injectSavedStateHandleViewModel() = Koject.runTest {
        val scenario = launchActivity<ComponentActivity>()
        var viewModel: SavedStateHandleViewModel? = null
        scenario.onActivity {
            val value by it.lazyViewModels<SavedStateHandleViewModel>()
            viewModel = value
        }
        assertNotNull(viewModel)
    }

    @Test
    fun injectSavedStateHandleViewModelWithDefaultArgs() = Koject.runTest {
        val scenario = launchActivity<ComponentActivity>()
        var viewModel: SavedStateHandleViewModel? = null
        scenario.onActivity {
            val extras = MutableCreationExtras(it.defaultViewModelCreationExtras)
            extras[DEFAULT_ARGS_KEY] = bundleOf("test-key" to "test-value")
            val value by it.lazyViewModels<SavedStateHandleViewModel>(
                extrasProducer = { extras },
            )
            viewModel = value
        }
        assertNotNull(viewModel)
        assertEquals("test-value", viewModel!!.savedStateHandle["test-key"])
    }

    @Test
    fun injectApplicationViewModel() = Koject.runTest {
        val scenario = launchActivity<ComponentActivity>()
        var viewModel1: ApplicationViewModel? = null
        var viewModel2: ApplicationWithSavedStateViewModel? = null
        scenario.onActivity {
            viewModel1 = it.lazyViewModels<ApplicationViewModel>().value
            viewModel2 = it.lazyViewModels<ApplicationWithSavedStateViewModel>().value
        }
        val expectedApplication = ApplicationProvider.getApplicationContext<Application>()
        assertNotNull(viewModel1)
        assertNotNull(viewModel2)
        assertEquals(expectedApplication, viewModel1!!.getApplication())
        assertEquals(expectedApplication, viewModel2!!.getApplication())
    }

    @Test
    fun injectContextViewModel() = Koject.runTest {
        val scenario = launchActivity<ComponentActivity>()
        var viewModel1: ContextViewModel? = null
        var viewModel2: ContextWithSavedStateViewModel? = null
        scenario.onActivity {
            viewModel1 = it.lazyViewModels<ContextViewModel>().value
            viewModel2 = it.lazyViewModels<ContextWithSavedStateViewModel>().value
        }
        val expectedContext = ApplicationProvider.getApplicationContext<Application>()
        assertNotNull(viewModel1)
        assertNotNull(viewModel2)
        assertEquals(expectedContext, viewModel1!!.context)
        assertEquals(expectedContext, viewModel2!!.context)
    }
}
