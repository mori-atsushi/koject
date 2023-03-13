package com.moriatsushi.koject.component

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Named
import com.moriatsushi.koject.Qualifier
import com.moriatsushi.koject.Singleton

/**
 * By implementing this, you can provide additional dependencies
 * for the [Component].
 *
 * All non-private properties are treated as providing.
 * ```
 * class SomeComponentExtras(
 *     // provide SomeClass1
 *     val someClass1: SomeClass1
 * ): ComponentExtras<SomeComponent> {
 *     // provide SomeClass2
 *     val someClass2: SomeClass2 = SomeClass2()
 *
 *     // provide SomeClass3
 *     val someClass3: SomeClass3
 *         get() = SomeClass3()
 * }
 * ```
 *
 * @[Qualifier] and @[Named] annotations can be used.
 * ```
 * class SomeComponentExtras: ComponentExtras<SomeComponent> {
 *     // provide with a qualifier
 *     @SomeQualifier
 *     val someClass2: SomeClass2 = SomeClass2()
 *
 *     // provide with a name
 *     @Named("some-name")
 *     val someClass3: SomeClass3
 *         get() = SomeClass3()
 * }
 * ```
 *
 * @[Singleton] is not available.
 *
 * @param T The class of the [Component] to create.
 */
@ExperimentalKojectApi
interface ComponentExtras<T : Any>
