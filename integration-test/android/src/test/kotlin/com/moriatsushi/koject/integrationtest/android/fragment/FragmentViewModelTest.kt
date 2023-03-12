package com.moriatsushi.koject.integrationtest.android.fragment

import android.app.Application
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import androidx.fragment.app.testing.launchFragment
import androidx.lifecycle.DEFAULT_ARGS_KEY
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.android.fragment.injectActivityViewModels
import com.moriatsushi.koject.android.fragment.lazyViewModels
import com.moriatsushi.koject.error.NotProvidedException
import com.moriatsushi.koject.integrationtest.android.runTest
import com.moriatsushi.koject.integrationtest.android.viewmodel.ApplicationViewModel
import com.moriatsushi.koject.integrationtest.android.viewmodel.ApplicationWithSavedStateViewModel
import com.moriatsushi.koject.integrationtest.android.viewmodel.ContextViewModel
import com.moriatsushi.koject.integrationtest.android.viewmodel.ContextWithSavedStateViewModel
import com.moriatsushi.koject.integrationtest.android.viewmodel.NotProvidedViewModel
import com.moriatsushi.koject.integrationtest.android.viewmodel.QualifierViewModel
import com.moriatsushi.koject.integrationtest.android.viewmodel.SampleViewModel
import com.moriatsushi.koject.integrationtest.android.viewmodel.SavedStateHandleViewModel
import com.moriatsushi.koject.integrationtest.android.viewmodel.ViewModelQualifier
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertIs
import kotlin.test.assertNotNull
import kotlin.test.assertSame
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FragmentViewModelTest {
    @Test
    fun lazyViewModel() = Koject.runTest {
        val scenario = launchFragment<Fragment>()
        scenario.onFragment {
            val viewModel: SampleViewModel by it.lazyViewModels()
            assertIs<SampleViewModel>(viewModel)
        }
    }

    @Test
    fun injectActivityViewModel() = Koject.runTest {
        val scenario = launchFragment<Fragment>()
        scenario.onFragment {
            val viewModel: SampleViewModel by it.injectActivityViewModels()
            assertIs<SampleViewModel>(viewModel)
        }
    }

    @Test
    fun injectSameViewModelAfterRecreate() = Koject.runTest {
        val scenario = launchFragment<Fragment>()
        lateinit var viewModel1: SampleViewModel
        lateinit var viewModel2: SampleViewModel

        scenario.onFragment {
            val viewModel: SampleViewModel by it.lazyViewModels()
            viewModel1 = viewModel
        }

        scenario.recreate()

        scenario.onFragment {
            val viewModel: SampleViewModel by it.lazyViewModels()
            viewModel2 = viewModel
        }

        assertSame(viewModel1, viewModel2)
    }

    @Test
    fun failedlazyViewModel_whenNotProvided() = Koject.runTest {
        val scenario = launchFragment<Fragment>()
        scenario.onFragment {
            val viewModel: NotProvidedViewModel by it.lazyViewModels()
            assertFailsWith<NotProvidedException> {
                print(viewModel)
            }
        }
    }

    @Test
    fun injectQualifierViewModel() = Koject.runTest {
        val scenario = launchFragment<Fragment>()
        var viewModel: QualifierViewModel? = null
        scenario.onFragment {
            val value by it.lazyViewModels<QualifierViewModel>(
                ViewModelQualifier(),
            )
            viewModel = value
        }
        assertNotNull(viewModel)
        assertEquals("QualifierViewModel", viewModel!!.name)
    }

    @Test
    fun injectSavedStateHandleViewModel() = Koject.runTest {
        val scenario = launchFragment<Fragment>()
        var viewModel: SavedStateHandleViewModel? = null
        scenario.onFragment {
            val value by it.lazyViewModels<SavedStateHandleViewModel>()
            viewModel = value
        }
        assertNotNull(viewModel)
    }

    @Test
    fun injectSavedStateHandleViewModelWithDefaultArgs() = Koject.runTest {
        val scenario = launchFragment<Fragment>()
        var viewModel: SavedStateHandleViewModel? = null
        scenario.onFragment {
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
    fun injectParentFragmentViewModel() = Koject.runTest {
        val scenario = launchFragment<Fragment>()
        var viewModelByParent: SavedStateHandleViewModel? = null
        var viewModelByChild: SavedStateHandleViewModel? = null
        val childFragment = Fragment()
        scenario.onFragment {
            it.childFragmentManager.commitNow {
                add(childFragment, "tag")
            }
            viewModelByParent = it.lazyViewModels<SavedStateHandleViewModel>().value
            viewModelByChild = childFragment.lazyViewModels<SavedStateHandleViewModel>(
                ownerProducer = { childFragment.requireParentFragment() },
            ).value
        }

        assertNotNull(viewModelByParent)
        assertNotNull(viewModelByChild)
        assertSame(viewModelByChild, viewModelByParent)
    }

    @Test
    fun injectApplicationViewModel() = Koject.runTest {
        val scenario = launchFragment<Fragment>()
        var viewModel1: ApplicationViewModel? = null
        var viewModel2: ApplicationWithSavedStateViewModel? = null
        scenario.onFragment {
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
        val scenario = launchFragment<Fragment>()
        var viewModel1: ContextViewModel? = null
        var viewModel2: ContextWithSavedStateViewModel? = null
        scenario.onFragment {
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
