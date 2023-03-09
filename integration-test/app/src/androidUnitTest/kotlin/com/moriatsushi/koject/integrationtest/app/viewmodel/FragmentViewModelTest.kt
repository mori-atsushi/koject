package com.moriatsushi.koject.integrationtest.app.viewmodel

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import androidx.fragment.app.testing.launchFragment
import androidx.lifecycle.DEFAULT_ARGS_KEY
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.android.fragment.injectActivityViewModels
import com.moriatsushi.koject.android.fragment.injectViewModels
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
class FragmentViewModelTest {
    @After
    fun clear() {
        Koject.stop()
    }

    @Test
    fun injectViewModel() {
        Koject.start()

        val scenario = launchFragment<Fragment>()
        scenario.onFragment {
            val viewModel: SampleViewModel by it.injectViewModels()
            assertIs<SampleViewModel>(viewModel)
        }
    }

    @Test
    fun injectActivityViewModel() {
        Koject.start()

        val scenario = launchFragment<Fragment>()
        scenario.onFragment {
            val viewModel: SampleViewModel by it.injectActivityViewModels()
            assertIs<SampleViewModel>(viewModel)
        }
    }

    @Test
    fun injectSameViewModelAfterRecreate() {
        Koject.start()

        val scenario = launchFragment<Fragment>()
        lateinit var viewModel1: SampleViewModel
        lateinit var viewModel2: SampleViewModel

        scenario.onFragment {
            val viewModel: SampleViewModel by it.injectViewModels()
            viewModel1 = viewModel
        }

        scenario.recreate()

        scenario.onFragment {
            val viewModel: SampleViewModel by it.injectViewModels()
            viewModel2 = viewModel
        }

        assertSame(viewModel1, viewModel2)
    }

    @Test
    fun failedInjectViewModel_whenNotProvided() {
        Koject.start()

        val scenario = launchFragment<Fragment>()
        scenario.onFragment {
            val viewModel: NotProvidedViewModel by it.injectViewModels()
            assertFailsWith<NotProvidedException> {
                print(viewModel)
            }
        }
    }

    @Test
    fun injectQualifierViewModel() {
        Koject.start()

        val scenario = launchFragment<Fragment>()
        var viewModel: QualifierViewModel? = null
        scenario.onFragment {
            val value by it.injectViewModels<QualifierViewModel>(ViewModelQualifier())
            viewModel = value
        }
        assertNotNull(viewModel)
        assertEquals("QualifierViewModel", viewModel!!.name)
    }

    @Test
    fun injectSavedStateHandleViewModel() {
        Koject.start()

        val scenario = launchFragment<Fragment>()
        var viewModel: SavedStateHandleViewModel? = null
        scenario.onFragment {
            val value by it.injectViewModels<SavedStateHandleViewModel>()
            viewModel = value
        }
        assertNotNull(viewModel)
    }

    @Test
    fun injectSavedStateHandleViewModelWithDefaultArgs() {
        Koject.start()

        val scenario = launchFragment<Fragment>()
        var viewModel: SavedStateHandleViewModel? = null
        scenario.onFragment {
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

    @Test
    fun injectParentFragmentViewModel() {
        Koject.start()

        val scenario = launchFragment<Fragment>()
        var viewModelByParent: SavedStateHandleViewModel? = null
        var viewModelByChild: SavedStateHandleViewModel? = null
        val childFragment = Fragment()
        scenario.onFragment {
            it.childFragmentManager.commitNow {
                add(childFragment, "tag")
            }
            viewModelByParent = it.injectViewModels<SavedStateHandleViewModel>().value
            viewModelByChild = childFragment.injectViewModels<SavedStateHandleViewModel>(
                ownerProducer = { childFragment.requireParentFragment() },
            ).value
        }

        assertNotNull(viewModelByParent)
        assertNotNull(viewModelByChild)
        assertSame(viewModelByChild, viewModelByParent)
    }
}
