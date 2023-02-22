package com.moriatsushi.koject

import com.moriatsushi.koject.internal.identifier.Identifier
import com.moriatsushi.koject.internal.identifier.of

/**
 * Inject an instance with resolved dependencies
 */
inline fun <reified T : Any> inject(): T {
    val id = Identifier.of<T>()
    return inject(id) as T
}

@PublishedApi
internal fun inject(id: Identifier): Any {
    return Koject.container.resolve(id)
}
