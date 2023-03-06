package com.moriatsushi.koject.integrationtest.viewmodel

import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.launchFragment
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.android.viewmodel.injectActivityViewModels
import com.moriatsushi.koject.android.viewmodel.injectViewModels
import com.moriatsushi.koject.error.NotProvidedException
import com.moriatsushi.koject.start
import kotlin.test.assertFailsWith
import kotlin.test.assertIs
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
}
