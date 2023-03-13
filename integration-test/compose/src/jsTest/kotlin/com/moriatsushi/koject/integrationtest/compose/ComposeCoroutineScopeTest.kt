package com.moriatsushi.koject.integrationtest.compose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.compose.rememberInject
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlinx.coroutines.isActive
import org.jetbrains.compose.web.testutils.ComposeWebExperimentalTestsApi
import org.jetbrains.compose.web.testutils.runTest

@OptIn(ComposeWebExperimentalTestsApi::class)
class ComposeCoroutineScopeTest {
    @Test
    fun successInject() = Koject.runTest {
        runTest {
            var value: ForComposeWithCoroutineScope? = null

            composition {
                value = rememberInject()
            }

            assertNotNull(value)
        }
    }

    @Test
    fun cancelScope_whenHidden() = Koject.runTest {
        runTest {
            var visible by mutableStateOf(true)
            var value: ForComposeWithCoroutineScope? = null

            composition {
                if (visible) {
                    value = rememberInject()
                }
            }

            assertTrue(value!!.scope.isActive)

            visible = false
            waitForRecompositionComplete()

            assertFalse(value!!.scope.isActive)
        }
    }
}
