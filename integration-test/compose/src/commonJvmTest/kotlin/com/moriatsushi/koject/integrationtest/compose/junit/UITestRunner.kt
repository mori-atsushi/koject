package com.moriatsushi.koject.integrationtest.compose.junit

import kotlin.reflect.KClass

expect annotation class RunWith(val value: KClass<out Runner>)
expect abstract class Runner
expect class UITestRunner : Runner
