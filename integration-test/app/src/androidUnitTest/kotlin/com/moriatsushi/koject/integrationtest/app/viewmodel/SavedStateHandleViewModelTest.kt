package com.moriatsushi.koject.integrationtest.app.viewmodel

import androidx.activity.ComponentActivity
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.android.viewmodel.injectViewModels
import com.moriatsushi.koject.start
import kotlin.test.assertNotNull
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SavedStateHandleViewModelTest {
    @After
    fun clear() {
        Koject.stop()
    }

    @Test
    fun injectViewModel() {
        Koject.start()

        val scenario = launchActivity<ComponentActivity>()
        var viewModel: SavedStateHandleViewModel? = null
        scenario.onActivity {
            val value by it.injectViewModels<SavedStateHandleViewModel>()
            viewModel = value
        }
        assertNotNull(viewModel)
    }
}
