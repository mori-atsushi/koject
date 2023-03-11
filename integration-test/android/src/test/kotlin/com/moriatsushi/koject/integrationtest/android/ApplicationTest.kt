package com.moriatsushi.koject.integrationtest.android

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.inject
import kotlin.test.assertSame
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ApplicationTest {
    @Test
    fun successInject() = Koject.runTest {
        val holder = inject<ApplicationHolder>()
        val expected = ApplicationProvider.getApplicationContext<Application>()
        assertSame(expected, holder.application)
        assertSame(expected, holder.context)
    }

    @Test
    fun successInject_toSingleton() = Koject.runTest {
        val holder = inject<ApplicationSingletonHolder>()
        val expected = ApplicationProvider.getApplicationContext<Application>()
        assertSame(expected, holder.application)
        assertSame(expected, holder.context)
    }
}
