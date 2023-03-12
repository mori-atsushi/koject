package com.moriatsushi.koject

import androidx.fragment.app.Fragment
import com.moriatsushi.koject.android.fragment.FragmentComponentExtras

/**
 * Inject an instance for [Fragment].
 *
 * @param qualifier Qualifier for identification.
 * Specify the instantiation of the annotation with [Qualifier].
 */
@OptIn(ExperimentalKojectApi::class)
inline fun <reified T : Any> Fragment.inject(
    qualifier: Any? = null,
): T {
    return inject(
        qualifier = qualifier,
        componentExtras = FragmentComponentExtras(this),
    )
}

/**
 * Inject an instance for [Fragment] lazily.
 *
 * @param qualifier Qualifier for identification.
 * Specify the instantiation of the annotation with [Qualifier].
 */
@OptIn(ExperimentalKojectApi::class)
inline fun <reified T : Any> Fragment.lazyInject(
    qualifier: Any? = null,
): Lazy<T> {
    return lazyInject(
        qualifier = qualifier,
        componentExtrasProducer = {
            FragmentComponentExtras(this)
        },
    )
}
