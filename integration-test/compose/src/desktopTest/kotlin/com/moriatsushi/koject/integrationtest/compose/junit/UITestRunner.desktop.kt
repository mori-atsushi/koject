package com.moriatsushi.koject.integrationtest.compose.junit

import kotlin.reflect.KClass

actual annotation class RunWith(actual val value: KClass<out Runner>) // no op
actual abstract class Runner // no op
actual class UITestRunner : Runner() // no op
