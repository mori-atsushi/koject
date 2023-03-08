package com.moriatsushi.koject

import com.moriatsushi.koject.component.Component
import com.moriatsushi.koject.component.ComponentExtras
import com.moriatsushi.koject.error.NotProvidedException
import com.moriatsushi.koject.internal.Identifier

/**
 * Inject an instance with resolved dependencies
 *
 * @param qualifier Qualifier for identification.
 *   Specify the instantiation of the annotation with [Qualifier].
 */
@OptIn(ExperimentalKojectApi::class)
inline fun <reified T : Any> inject(
    qualifier: Any? = null,
): T {
    return inject(qualifier, null)
}

/**
 * Inject an instance with resolved dependencies (experimental)
 *
 * @param qualifier Qualifier for identification.
 *   Specify the instantiation of the annotation with [Qualifier].
 * @param componentExtras Specify [ComponentExtras] to create [Component].
 */
@ExperimentalKojectApi
inline fun <reified T : Any> inject(
    qualifier: Any? = null,
    componentExtras: Any? = null,
): T {
    val id = Identifier.of<T>(qualifier)
    return injectOrNull(id, componentExtras) as? T
        ?: throw NotProvidedException("$id is not provided")
}

/**
 * Inject an [Named] instance with resolved dependencies
 *
 * @param name name of [Named]
 */
inline fun <reified T : Any> inject(name: String): T {
    return inject(Named(name))
}

/**
 * Inject an [Named] instance with resolved dependencies (experimental)
 *
 * @param name name of [Named]
 * @param componentExtras Specify [ComponentExtras] to create [Component].
 */
@ExperimentalKojectApi
inline fun <reified T : Any> inject(
    name: String,
    componentExtras: Any? = null,
): T {
    return inject(Named(name), componentExtras)
}

@PublishedApi
internal fun injectOrNull(
    id: Identifier,
    componentExtras: Any?,
): Any? {
    return Koject.container.resolve(id, componentExtras)
}
