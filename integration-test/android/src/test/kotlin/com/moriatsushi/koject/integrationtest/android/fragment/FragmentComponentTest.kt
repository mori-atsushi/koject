package com.moriatsushi.koject.integrationtest.android.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.launchFragment
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
    @Test
    fun lazyInject_forFragment() = Koject.runTest {
        val scenario = launchFragment<Fragment>()
        scenario.onFragment {
            val value1: ForFragment by it.lazyInject()
            val value2: ForFragmentWithActivity by it.lazyInject()
            val value3: ForFragmentHolder by it.lazyInject()

            assertEquals(it, value1.fragment)
            assertEquals(it, value2.fragment)
            assertEquals(it.requireActivity(), value2.fragmentActivity)
            assertEquals(it.requireActivity(), value2.componentActivity)
            assertEquals(it.requireActivity(), value2.activity)

            assertEquals(it, value3.forFragment.fragment)
            assertEquals(it, value3.forFragmentWithActivity.fragment)
        }
    }

    @Test
    fun inject_forFragment() = Koject.runTest {
        val scenario = launchFragment<Fragment>()
        scenario.onFragment {
            val value1: ForFragment = it.inject()
            val value2: ForFragmentWithActivity = it.inject()
            val value3: ForFragmentHolder = it.inject()

            assertEquals(it, value1.fragment)
            assertEquals(it, value2.fragment)
            assertEquals(it.requireActivity(), value2.fragmentActivity)
            assertEquals(it.requireActivity(), value2.componentActivity)
            assertEquals(it.requireActivity(), value2.activity)

            assertEquals(it, value3.forFragment.fragment)
            assertEquals(it, value3.forFragmentWithActivity.fragment)
        }
    }

    @Test
    fun inject_notAttached() = Koject.runTest {
        val fragment = Fragment()
        val value = fragment.inject<ForFragment>()
        assertEquals(fragment, value.fragment)

        assertFailsWith<IllegalStateException> {
            fragment.inject<ForFragmentWithActivity>()
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
