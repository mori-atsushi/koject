package com.moriatsushi.koject.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.moriatsushi.koject.Named
import com.moriatsushi.koject.Qualifier
import com.moriatsushi.koject.inject

/**
 * Inject an instance with resolved dependencies
 *
 * @param qualifier Qualifier for identification.
 *   Specify the instantiation of the annotation with [Qualifier].
 */
@Composable
inline fun <reified T : Any> rememberInject(
    qualifier: Any? = null,
): T {
    return remember(qualifier) {
        inject(qualifier)
    }
}

/**
 * Inject an [Named] instance with resolved dependencies
 *
 * @param name name of [Named]
 */
@Composable
inline fun <reified T : Any> rememberInject(name: String): T {
    return remember(name) {
        inject(name)
    }
}
