package com.moriatsushi.koject

import com.moriatsushi.koject.error.NotProvidedException
import com.moriatsushi.koject.internal.Extras
import com.moriatsushi.koject.internal.Identifier

/**
 * Inject an instance with resolved dependencies
 */
@OptIn(ExperimentalKojectApi::class)
inline fun <reified T : Any> inject(
    qualifier: Any? = null,
): T {
    return inject(qualifier) {}
}

/**
 * Inject an instance with resolved dependencies
 */
@ExperimentalKojectApi
inline fun <reified T : Any> inject(
    qualifier: Any? = null,
    noinline extras: (ExtrasBuilder.() -> Unit)? = null,
): T {
    val id = Identifier.of<T>(qualifier)
    return injectOrNull(id, extras) as? T
        ?: throw NotProvidedException("$id is not provided")
}

/**
 * Inject an [Named] instance with resolved dependencies
 */
inline fun <reified T : Any> inject(name: String): T {
    return inject(Named(name))
}

/**
 * Inject an [Named] instance with resolved dependencies
 */
@ExperimentalKojectApi
inline fun <reified T : Any> inject(
    name: String,
    noinline extras: (ExtrasBuilder.() -> Unit)? = null,
): T {
    return inject(Named(name), extras)
}

@ExperimentalKojectApi
@PublishedApi
internal fun injectOrNull(
    id: Identifier,
    extras: (ExtrasBuilder.() -> Unit)?,
): Any? {
    return Koject.container.resolve(
        id,
        Extras.of(extras),
    )
}
