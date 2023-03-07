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
    return inject(qualifier, null)
}

/**
 * Inject an instance with resolved dependencies (experimental)
 *
 * @param qualifier Qualifier for identification.
 *   Specify the instantiation of the annotation with [Qualifier].
 * @param componentArguments Specify [Component.Arguments] to create [Component].
 */
@ExperimentalKojectApi
inline fun <reified T : Any> inject(
    qualifier: Any? = null,
    componentArguments: Any? = null,
): T {
    val id = Identifier.of<T>(qualifier)
    return injectOrNull(id, componentArguments) as? T
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
 * @param componentArguments Specify [Component.Arguments] to create [Component].
 */
@ExperimentalKojectApi
inline fun <reified T : Any> inject(
    name: String,
    componentArguments: Any? = null,
): T {
    return inject(Named(name), componentArguments)
}

@PublishedApi
internal fun injectOrNull(
    id: Identifier,
    componentArguments: Any?,
): Any? {
    return Koject.container.resolve(id, componentArguments)
}
