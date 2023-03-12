package com.moriatsushi.koject.integrationtest.compose

import android.app.Application
import androidx.compose.ui.platform.LocalContext
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.compose.rememberInject
import kotlin.test.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AndroidComposeComponentTest {
    private val applicationContext = ApplicationProvider.getApplicationContext<Application>()

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun inject_composeComponent() = Koject.runTest {
        composeTestRule.setContent {
            val localContext = LocalContext.current
            val value: ForComposeWithContext = rememberInject()
            assertEquals(localContext, value.composeContext)
            assertEquals(applicationContext, value.applicationContext)
        }
    }
}
