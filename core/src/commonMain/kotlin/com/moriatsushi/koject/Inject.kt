package com.moriatsushi.koject

import com.moriatsushi.koject.error.NotProvidedException
import com.moriatsushi.koject.internal.identifier.Identifier

/**
 * Inject an instance with resolved dependencies
 */
inline fun <reified T : Any> inject(qualifier: Any? = null): T {
    val id = Identifier.of<T>(qualifier)
    return injectOrNull(id) as? T
        ?: throw NotProvidedException("$id is not provided")
}

/**
 * Inject an [Named] instance with resolved dependencies
 */
inline fun <reified T : Any> inject(name: String): T {
    return inject(Named(name))
}

@PublishedApi
internal fun injectOrNull(id: Identifier): Any? {
    return Koject.container.resolve(id)
}
