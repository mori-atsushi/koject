package com.moriatsushi.koject.integrationtest.compose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.compose.rememberInject
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertSame
import org.jetbrains.compose.web.testutils.ComposeWebExperimentalTestsApi
import org.jetbrains.compose.web.testutils.runTest

@OptIn(ComposeWebExperimentalTestsApi::class)
class ComposeTest {
    @Test
    fun successInject() = Koject.runTest {
        runTest {
            var value: CommonClass? = null

            composition {
                value = rememberInject()
            }

            assertNotNull(value)
        }
    }

    @Test
    fun successInject_singleton() = Koject.runTest {
        runTest {
            var value1: CommonSingleton? = null
            var value2: CommonSingleton? = null

            composition {
                value1 = rememberInject()
                value2 = rememberInject()
            }

            assertSame(value1, value2)
        }
    }

    @Test
    fun rememberValue_whileRecompose() = Koject.runTest {
        runTest {
            var count by mutableStateOf(0)
            val values = mutableListOf<CommonClass>()

            composition {
                println(count)
                val value = rememberInject<CommonClass>()
                values.add(value)
            }

            waitForRecompositionComplete()

            count = 1 // recompose
            waitForRecompositionComplete()

            assertEquals(2, values.size)
            assertSame(values[0], values[1])
        }
    }
}
