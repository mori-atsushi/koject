package com.moriatsushi.koject.integrationtest.android.fragment

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.launchFragment
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
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FragmentComponentTest {
    private val applicationContext = ApplicationProvider.getApplicationContext<Application>()

    @Test
    fun lazyInject_forFragment() = Koject.runTest {
        val scenario = launchFragment<Fragment>()
        scenario.onFragment {
            val value1: ForFragment by it.lazyInject()
            val value2: ForFragmentActivity by it.lazyInject()
            val value3: ForFragmentContext by it.lazyInject()
            val value4: ForFragmentHolder by it.lazyInject()

            assertEquals(it, value1.fragment)

            assertEquals(it.requireActivity(), value2.fragmentActivity)
            assertEquals(it.requireActivity(), value2.componentActivity)
            assertEquals(it.requireActivity(), value2.activity)

            assertEquals(applicationContext, value3.applicationContext)
            assertEquals(it.requireActivity(), value3.activityContext)

            assertEquals(it, value4.forFragment.fragment)
        }
    }

    @Test
    fun inject_forFragment() = Koject.runTest {
        val scenario = launchFragment<Fragment>()
        scenario.onFragment {
            val value1: ForFragment = it.inject()
            val value2: ForFragmentActivity = it.inject()
            val value3: ForFragmentContext = it.inject()
            val value4: ForFragmentHolder = it.inject()

            assertEquals(it, value1.fragment)

            assertEquals(it.requireActivity(), value2.fragmentActivity)
            assertEquals(it.requireActivity(), value2.componentActivity)
            assertEquals(it.requireActivity(), value2.activity)

            assertEquals(applicationContext, value3.applicationContext)
            assertEquals(it.requireActivity(), value3.activityContext)

            assertEquals(it, value4.forFragment.fragment)
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
