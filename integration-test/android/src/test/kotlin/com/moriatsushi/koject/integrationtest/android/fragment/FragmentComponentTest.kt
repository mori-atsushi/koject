package com.moriatsushi.koject.integrationtest.android.fragment

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.launchFragment
import androidx.lifecycle.lifecycleScope
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.integrationtest.android.NormalClass
import com.moriatsushi.koject.integrationtest.android.SingletonClass
import com.moriatsushi.koject.integrationtest.android.runTest
import com.moriatsushi.koject.lazyInject
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertIs
import kotlin.test.assertSame
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FragmentComponentTest {
    private val applicationContext = ApplicationProvider.getApplicationContext<Application>()

    @Test
    fun lazyInject_forFragment() = Koject.runTest {
        val scenario = launchFragment<Fragment>()
        scenario.onFragment {
            val value: ForFragment by it.lazyInject()
            val holder: ForFragmentHolder by it.lazyInject()

            assertSame(it, value.fragment)
            assertSame(it, holder.forFragment.fragment)
        }
    }

    fun lazyInject_activity() = Koject.runTest {
        val scenario = launchFragment<Fragment>()
        scenario.onFragment {
            val value: ForFragmentActivity by it.lazyInject()

            assertSame(it.requireActivity(), value.fragmentActivity)
            assertSame(it.requireActivity(), value.componentActivity)
            assertSame(it.requireActivity(), value.activity)
        }
    }

    fun lazyInject_context() = Koject.runTest {
        val scenario = launchFragment<Fragment>()
        scenario.onFragment {
            val value: ForFragmentContext by it.lazyInject()

            assertSame(applicationContext, value.applicationContext)
            assertSame(it.requireActivity(), value.activityContext)
        }
    }

    fun lazyInject_coroutineScope() = Koject.runTest {
        val scenario = launchFragment<Fragment>()
        scenario.onFragment {
            val value: ForFragmentCoroutineScope by it.lazyInject()

            assertSame(it.lifecycleScope, value.coroutineScope)
            assertSame(it.viewLifecycleOwner.lifecycleScope, value.viewCoroutineScope)
        }
    }

    @Test
    fun inject_forFragment() = Koject.runTest {
        val scenario = launchFragment<Fragment>()
        scenario.onFragment {
            val value: ForFragment = it.inject()
            val holder: ForFragmentHolder = it.inject()

            assertSame(it, value.fragment)
            assertSame(it, holder.forFragment.fragment)
        }
    }

    @Test
    fun inject_notAttached() = Koject.runTest {
        val fragment = Fragment()
        val value = fragment.inject<ForFragment>()
        assertEquals(fragment, value.fragment)

        assertFailsWith<IllegalStateException> {
            fragment.inject<ForFragmentActivity>()
        }

        assertFailsWith<IllegalStateException> {
            fragment.inject<ForFragmentContext>()
        }

        assertFailsWith<IllegalStateException> {
            fragment.inject<ForFragmentCoroutineScope>()
        }
    }

    @Test
    fun lazyInject_normalClass() = Koject.runTest {
        val scenario = launchFragment<Fragment>()
        scenario.onFragment {
            val normalClass: NormalClass by it.lazyInject()
            val singletonClass: SingletonClass by it.lazyInject()

            assertIs<NormalClass>(normalClass)
            assertIs<SingletonClass>(singletonClass)
        }
    }

    @Test
    fun inject_normalClass() = Koject.runTest {
        val scenario = launchFragment<Fragment>()
        scenario.onFragment {
            val normalClass: NormalClass = it.inject()
            val singletonClass: SingletonClass = it.inject()

            assertIs<NormalClass>(normalClass)
            assertIs<SingletonClass>(singletonClass)
        }
    }
}
