package com.moriatsushi.koject.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Named
import com.moriatsushi.koject.Qualifier
import com.moriatsushi.koject.component.Component
import com.moriatsushi.koject.component.ComponentExtras
import com.moriatsushi.koject.inject

@OptIn(ExperimentalKojectApi::class)
val LocalComposeComponentExtras = staticCompositionLocalOf<ComponentExtras<*>?> { null }

/**
 * Inject an instance with resolved dependencies
 *
 * ```
 * @Composable
 * fun Sample(
 *     controller: SampleController = rememberInject()
 * ) {
 *      /* ... */
 * }
 * ```
 *
 * @param componentExtras Specify [ComponentExtras] to create [Component].
 */
@OptIn(ExperimentalKojectApi::class)
@Composable
inline fun <reified T : Any> rememberInject(
    componentExtras: ComponentExtras<*>? = LocalComposeComponentExtras.current,
): T {
    val finalComponentExtras = componentExtras ?: composeComponentExtras()
    return remember {
        inject(componentExtras = finalComponentExtras)
    }
}

/**
 * Inject an instance with resolved dependencies (experimental)
 *
 * ```
 * @Composable
 * fun Sample(
 *     controller: SampleController = rememberInject()
 * ) {
 *     /* ... */
 * }
 * ```
 *
 * @param qualifier Qualifier for identification.
 *   Specify the instantiation of the annotation with [Qualifier].
 * @param componentExtras Specify [ComponentExtras] to create [Component].
 */
@ExperimentalKojectApi
@Composable
inline fun <reified T : Any> rememberInject(
    qualifier: Any?,
    componentExtras: ComponentExtras<*>? = LocalComposeComponentExtras.current,
): T {
    val finalComponentExtras = componentExtras ?: composeComponentExtras()
    return remember(qualifier) {
        inject(qualifier, finalComponentExtras)
    }
}

/**
 * Inject an [Named] instance with resolved dependencies
 *
 * @param name name of [Named]
 */
@Deprecated(
    message = "The method of specifying qualifier has been unified.",
    replaceWith = ReplaceWith(
        "rememberInject(Named(name))",
        "com.moriatsushi.koject.Named",
    ),
)
@Composable
inline fun <reified T : Any> rememberInject(
    name: String,
): T {
    return remember(name) {
        inject(name)
    }
}

/**
 * Inject an [Named] instance with resolved dependencies (experimental)
 *
 * @param name name of [Named]
 * @param componentExtras Specify [ComponentExtras] to create [Component].
 */
@Deprecated(
    message = "The method of specifying qualifier has been unified.",
    replaceWith = ReplaceWith(
        "rememberInject(Named(name), componentExtras)",
        "com.moriatsushi.koject.Named",
    ),
)
@ExperimentalKojectApi
@Composable
inline fun <reified T : Any> rememberInject(
    name: String,
    componentExtras: ComponentExtras<*>? = LocalComposeComponentExtras.current,
): T {
    val finalComponentExtras = componentExtras ?: composeComponentExtras()
    return remember(name) {
        inject(name, finalComponentExtras)
    }
}
