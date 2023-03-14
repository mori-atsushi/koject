package com.moriatsushi.koject.integrationtest.compose

import android.app.Application
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.compose.LocalComposeComponentExtras
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

    @Test
    fun inject_customComposeComponent() = Koject.runTest {
        composeTestRule.setContent {
            val localContext = LocalContext.current
            val coroutineScope = rememberCoroutineScope()
            val extras = remember {
                CustomComposeComponentExtras(
                    context = localContext,
                    coroutineScope = coroutineScope,
                )
            }

            val value: ForCustomComposeWithContext = rememberInject(extras)
            assertEquals(localContext, value.composeContext)
            assertEquals(applicationContext, value.applicationContext)

            CompositionLocalProvider(
                LocalComposeComponentExtras provides extras,
            ) {
                val value2: ForCustomComposeWithContext2 = rememberInject()
                assertEquals(coroutineScope, value2.coroutineScope)
            }
        }
    }
}
