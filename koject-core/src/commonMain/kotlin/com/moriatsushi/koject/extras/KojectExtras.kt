package com.moriatsushi.koject.extras

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Named
import com.moriatsushi.koject.Qualifier
import com.moriatsushi.koject.Singleton

/**
 * Implement this interface to provide additional dependencies.
 *
 * All non-private properties are treated as providing.
 * ```
 * class GlobalExtras(
 *     // provide SomeClass1
 *     val someClass1: SomeClass1
 * ): KojectExtras {
 *     // provide SomeClass2
 *     val someClass2: SomeClass2 = SomeClass2()
 *
 *     // provide SomeClass3
 *     val someClass3: SomeClass3
 *         get() = SomeClass3()
 * }
 * ```
 *
 * @[Singleton], @[Qualifier] and @[Named] annotations can be used.
 * ```
 * class GlobalExtras(
 *     // provide as singleton
 *     @Singleton
 *     val someClass1: SomeClass1
 * ): KojectExtras {
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
 */
@ExperimentalKojectApi
interface KojectExtras
