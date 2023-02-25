package com.moriatsushi.koject

import com.moriatsushi.koject.error.NotProvidedException
import com.moriatsushi.koject.internal.identifier.Identifier

/**
 * Inject an instance with resolved dependencies
 */
inline fun <reified T : Any> inject(name: String? = null): T {
    val id = Identifier.of<T>(name)
    return injectOrNull(id) as? T
        ?: throw NotProvidedException("$id is not provided")
}

@PublishedApi
internal fun injectOrNull(id: Identifier): Any? {
    return Koject.container.resolve(id)
}
