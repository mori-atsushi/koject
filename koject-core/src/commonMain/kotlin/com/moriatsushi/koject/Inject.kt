package com.moriatsushi.koject

import com.moriatsushi.koject.component.Component
import com.moriatsushi.koject.component.ComponentExtras
import com.moriatsushi.koject.error.NotProvidedException
import com.moriatsushi.koject.internal.Identifier

/**
 * Inject an instance with resolved dependencies.
 *
 * @param qualifier Qualifier for identification.
 * Specify the instantiation of the annotation with [Qualifier].
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
 * Specify the instantiation of the annotation with [Qualifier].
 * @param componentExtras Specify [ComponentExtras] to create [Component].
 */
@ExperimentalKojectApi
inline fun <reified T : Any> inject(
    qualifier: Any? = null,
    componentExtras: ComponentExtras<*>? = null,
): T {
    val id = Identifier.of<T>(qualifier)
    return inject(id, componentExtras)
}

/**
 * Inject an instance with lazily resolved dependencies.
 *
 * @param qualifier Qualifier for identification.
 * Specify the instantiation of the annotation with [Qualifier].
 */
@OptIn(ExperimentalKojectApi::class)
inline fun <reified T : Any> lazyInject(
    qualifier: Any? = null,
): Lazy<T> {
    return lazyInject(qualifier, null)
}

/**
 * Inject an instance with lazily resolved dependencies (experimental).
 *
 * @param qualifier Qualifier for identification.
 * Specify the instantiation of the annotation with [Qualifier].
 * @param componentExtrasProducer Specify [ComponentExtras] to create [Component].
 */
@ExperimentalKojectApi
inline fun <reified T : Any> lazyInject(
    qualifier: Any? = null,
    noinline componentExtrasProducer: (() -> ComponentExtras<*>)? = null,
): Lazy<T> {
    val id = Identifier.of<T>(qualifier)
    return lazy { inject(id, componentExtrasProducer?.invoke()) }
}

/**
 * Inject an [Named] instance with resolved dependencies.
 *
 * @param name name of [Named]
 */
@Deprecated(
    message = "The method of specifying qualifier has been unified.",
    replaceWith = ReplaceWith(
        "inject(Named(name))",
        "com.moriatsushi.koject.Named",
    ),
)
inline fun <reified T : Any> inject(name: String): T {
    return inject(Named(name))
}

@ExperimentalKojectApi
@PublishedApi
internal fun <T : Any> inject(
    id: Identifier,
    componentExtras: ComponentExtras<*>? = null,
): T {
    val resolved = Koject.container.resolve(id, componentExtras)
        ?: throw NotProvidedException("$id is not provided")
    @Suppress("UNCHECKED_CAST")
    return resolved as T
}
