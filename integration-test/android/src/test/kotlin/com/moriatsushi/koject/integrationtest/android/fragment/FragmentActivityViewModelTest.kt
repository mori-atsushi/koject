package com.moriatsushi.koject.integrationtest.android.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commitNow
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.android.activity.lazyViewModels
import com.moriatsushi.koject.android.fragment.injectActivityViewModels
import com.moriatsushi.koject.integrationtest.android.runTest
import com.moriatsushi.koject.integrationtest.android.viewmodel.SampleViewModel
import kotlin.test.assertNotNull
import kotlin.test.assertSame
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FragmentActivityViewModelTest {
    @Test
    fun injectActivityViewModel() = Koject.runTest {
        val scenario = launchActivity<FragmentActivity>()
        val fragment = Fragment()
        var viewModelByActivity: SampleViewModel? = null
        var viewModelByFragment: SampleViewModel? = null

        scenario.onActivity {
            viewModelByActivity = it.lazyViewModels<SampleViewModel>().value
            it.supportFragmentManager.commitNow {
                add(fragment, "tag")
            }
            viewModelByFragment = fragment.injectActivityViewModels<SampleViewModel>().value
        }

        assertNotNull(viewModelByActivity)
        assertNotNull(viewModelByFragment)
        assertSame(viewModelByActivity, viewModelByFragment)
    }
}
