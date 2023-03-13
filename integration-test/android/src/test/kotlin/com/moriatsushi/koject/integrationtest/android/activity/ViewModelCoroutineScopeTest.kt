package com.moriatsushi.koject.integrationtest.android.activity

import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.android.activity.lazyViewModels
import com.moriatsushi.koject.integrationtest.android.runTest
import com.moriatsushi.koject.integrationtest.android.viewmodel.CoroutineScopeViewModel
import com.moriatsushi.koject.integrationtest.android.viewmodel.ForViewModelHolder
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlinx.coroutines.isActive
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ViewModelCoroutineScopeTest {
    @Test
    fun lazyInject_coroutineScopeViewModel() = Koject.runTest {
        var viewModel: CoroutineScopeViewModel? = null
        val scenario = launchActivity<ComponentActivity>()
        scenario.onActivity {
            viewModel = it.lazyViewModels<CoroutineScopeViewModel>().value
        }

        assertNotNull(viewModel)
        assertTrue(viewModel!!.coroutineScope.isActive)

        scenario.moveToState(Lifecycle.State.DESTROYED)

        assertFalse(viewModel!!.coroutineScope.isActive)
    }

    @Test
    fun lazyInject_forViewModelHolder() = Koject.runTest {
        var viewModel: ForViewModelHolder? = null
        val scenario = launchActivity<ComponentActivity>()
        scenario.onActivity {
            viewModel = it.lazyViewModels<ForViewModelHolder>().value
        }

        assertNotNull(viewModel)
        assertTrue(viewModel!!.forViewModel.coroutineScope.isActive)

        scenario.moveToState(Lifecycle.State.DESTROYED)

        assertFalse(viewModel!!.forViewModel.coroutineScope.isActive)
    }
}
