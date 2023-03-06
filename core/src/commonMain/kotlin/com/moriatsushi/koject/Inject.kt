package com.moriatsushi.koject

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
    return inject(qualifier) {}
}

/**
 * Inject an instance with resolved dependencies (experimental)
 *
 * @param qualifier Qualifier for identification.
 *   Specify the instantiation of the annotation with [Qualifier].
 * @param extras [Dynamic] dependencies
 */
@ExperimentalKojectApi
inline fun <reified T : Any> inject(
    qualifier: Any? = null,
    extras: Extras = Extras.empty,
): T {
    val id = Identifier.of<T>(qualifier)
    return injectOrNull(id, extras) as? T
        ?: throw NotProvidedException("$id is not provided")
}

/**
 * Inject an instance with resolved dependencies (experimental)
 *
 * @param qualifier Qualifier for identification.
 *   Specify the instantiation of the annotation with [Qualifier].
 * @param extras [Dynamic] dependencies
 */
@ExperimentalKojectApi
inline fun <reified T : Any> inject(
    qualifier: Any? = null,
    crossinline extras: ExtrasBuilder.() -> Unit,
): T {
    return inject(qualifier, buildExtras(extras))
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
 * @param extras [Dynamic] dependencies
 */
@ExperimentalKojectApi
inline fun <reified T : Any> inject(
    name: String,
    extras: Extras = Extras.empty,
): T {
    return inject(Named(name), extras)
}

/**
 * Inject an [Named] instance with resolved dependencies (experimental)
 *
 * @param name name of [Named]
 * @param extras [Dynamic] dependencies
 */
@ExperimentalKojectApi
inline fun <reified T : Any> inject(
    name: String,
    crossinline extras: ExtrasBuilder.() -> Unit,
): T {
    return inject(Named(name), extras)
}

@ExperimentalKojectApi
@PublishedApi
internal fun injectOrNull(
    id: Identifier,
    extras: Extras,
): Any? {
    return Koject.container.resolve(id, extras)
}
