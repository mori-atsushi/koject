package com.moriatsushi.koject

import androidx.activity.ComponentActivity
import com.moriatsushi.koject.android.activity.ActivityComponentExtras

/**
 * Inject an instance for [ComponentActivity].
 *
 * @param qualifier Qualifier for identification.
 * Specify the instantiation of the annotation with [Qualifier].
 */
@OptIn(ExperimentalKojectApi::class)
inline fun <reified T : Any> ComponentActivity.inject(
    qualifier: Any? = null,
): T {
    return inject(
        qualifier = qualifier,
        componentExtras = ActivityComponentExtras(this),
    )
}

/**
 * Inject an instance for [ComponentActivity] lazily.
 *
 * @param qualifier Qualifier for identification.
 * Specify the instantiation of the annotation with [Qualifier].
 */
@OptIn(ExperimentalKojectApi::class)
inline fun <reified T : Any> ComponentActivity.lazyInject(
    qualifier: Any? = null,
): Lazy<T> {
    return lazyInject(
        qualifier = qualifier,
        componentExtras = ActivityComponentExtras(this),
    )
}
