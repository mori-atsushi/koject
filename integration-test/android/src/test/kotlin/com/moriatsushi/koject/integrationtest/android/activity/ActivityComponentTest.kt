package com.moriatsushi.koject.integrationtest.android.activity

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.integrationtest.android.NormalClass
import com.moriatsushi.koject.integrationtest.android.SingletonClass
import com.moriatsushi.koject.integrationtest.android.runTest
import com.moriatsushi.koject.lazyInject
import kotlin.test.assertEquals
import kotlin.test.assertIs
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ActivityComponentTest {
    private val applicationContext = ApplicationProvider.getApplicationContext<Application>()

    @Test
    fun lazyInject_forActivity() = Koject.runTest {
        val scenario = launchActivity<ComponentActivity>()
        scenario.onActivity {
            val componentClass: ForActivity by it.lazyInject()
            val componentHolder: ForActivityHolder by it.lazyInject()

            assertEquals(it, componentClass.componentActivity)
            assertEquals(it, componentClass.activity)
            assertEquals(it, componentClass.activityContext)
            assertEquals(applicationContext, componentClass.applicationContext)

            assertEquals(it, componentHolder.forActivity.componentActivity)
        }
    }

    @Test
    fun inject_forActivity() = Koject.runTest {
        val scenario = launchActivity<ComponentActivity>()
        scenario.onActivity {
            val componentClass: ForActivity = it.inject()
            val componentHolder: ForActivityHolder = it.inject()

            assertEquals(it, componentClass.componentActivity)
            assertEquals(it, componentClass.activity)
            assertEquals(it, componentClass.activityContext)
            assertEquals(applicationContext, componentClass.applicationContext)

            assertEquals(it, componentHolder.forActivity.componentActivity)
        }
    }

    @Test
    fun lazyInject_normalClass() = Koject.runTest {
        val scenario = launchActivity<ComponentActivity>()
        scenario.onActivity {
            val normalClass: NormalClass by it.lazyInject()
            val singletonClass: SingletonClass by it.lazyInject()

            assertIs<NormalClass>(normalClass)
            assertIs<SingletonClass>(singletonClass)
        }
    }

    @Test
    fun inject_normalClass() = Koject.runTest {
        val scenario = launchActivity<ComponentActivity>()
        scenario.onActivity {
            val normalClass: NormalClass = it.inject()
            val singletonClass: SingletonClass = it.inject()

            assertIs<NormalClass>(normalClass)
            assertIs<SingletonClass>(singletonClass)
        }
    }
}
