package com.moriatsushi.koject

import com.moriatsushi.koject.internal.ExtrasImpl
import com.moriatsushi.koject.internal.Identifier

/**
 * Create [Extras]
 */
@ExperimentalKojectApi
class ExtrasBuilder {
    private val map = mutableMapOf<Identifier, () -> Any>()

    /**
     * Provide at runtime
     *
     * @param qualifier Qualifier for identification.
     *   Specify the instantiation of the annotation with [Qualifier].
     * @param builder How to create an instance
     */
    inline fun <reified T : Any> provides(
        qualifier: Any? = null,
        noinline builder: () -> T,
    ) {
        val identifier = Identifier.of<T>(qualifier)
        provides(identifier, builder)
    }

    /**
     * Provide at runtime
     *
     * @param name [Named] Qualifier.
     * @param builder How to create an instance
     */
    inline fun <reified T : Any> provides(
        name: String,
        noinline builder: () -> T,
    ) {
        provides(Named(name), builder)
    }

    @PublishedApi
    internal fun provides(identifier: Identifier, builder: () -> Any) {
        map[identifier] = builder
    }

    fun build(): Extras {
        return if (map.isNotEmpty()) {
            ExtrasImpl(map)
        } else {
            Extras.empty
        }
    }
}

/**
 * Create [Extras]
 */
@ExperimentalKojectApi
inline fun buildExtras(
    crossinline builder: ExtrasBuilder.() -> Unit,
): Extras {
    return ExtrasBuilder().apply { builder() }.build()
}
