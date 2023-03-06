package com.moriatsushi.koject.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.moriatsushi.koject.Dynamic
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Extras
import com.moriatsushi.koject.ExtrasBuilder
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
 * Inject an instance with resolved dependencies (experimental)
 *
 * @param qualifier Qualifier for identification.
 *   Specify the instantiation of the annotation with [Qualifier].
 * @param extras [Dynamic] dependencies
 */
@Composable
@ExperimentalKojectApi
inline fun <reified T : Any> rememberInject(
    qualifier: Any? = null,
    extras: Extras = Extras.empty,
): T {
    return remember(qualifier) {
        inject(qualifier, extras)
    }
}

/**
 * Inject an instance with resolved dependencies (experimental)
 *
 * @param qualifier Qualifier for identification.
 *   Specify the instantiation of the annotation with [Qualifier].
 * @param extras [Dynamic] dependencies
 */
@Composable
@ExperimentalKojectApi
inline fun <reified T : Any> rememberInject(
    qualifier: Any? = null,
    crossinline extras: ExtrasBuilder.() -> Unit,
): T {
    return remember(qualifier) {
        inject(qualifier, extras)
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

/**
 * Inject an [Named] instance with resolved dependencies (experimental)
 *
 * @param name name of [Named]
 * @param extras [Dynamic] dependencies
 */
@Composable
@ExperimentalKojectApi
inline fun <reified T : Any> rememberInject(
    name: String,
    extras: Extras = Extras.empty,
): T {
    return remember(name) {
        inject(name, extras)
    }
}

/**
 * Inject an [Named] instance with resolved dependencies (experimental)
 *
 * @param name name of [Named]
 * @param extras [Dynamic] dependencies
 */
@Composable
@ExperimentalKojectApi
inline fun <reified T : Any> rememberInject(
    name: String,
    crossinline extras: ExtrasBuilder.() -> Unit,
): T {
    return remember(name) {
        inject(name, extras)
    }
}
