package com.moriatsushi.koject.android.fragment

import android.app.Activity
import androidx.fragment.app.Fragment
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.component.Component

/**
 * Mark it as a type to inject into [Fragment].
 *
 * By using with @[Provides], you can inject [Fragment]
 * or [Activity].
 * ```
 * @Provides
 * @FragmentComponent
 * class ForFragmentClass(
 *     private val fragment: Fragment
 * )
 * ```
 */
@OptIn(ExperimentalKojectApi::class)
@Component
@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
annotation class FragmentComponent
