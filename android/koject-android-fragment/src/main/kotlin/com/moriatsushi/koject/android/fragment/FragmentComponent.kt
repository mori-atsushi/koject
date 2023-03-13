package com.moriatsushi.koject.android.fragment

import androidx.fragment.app.Fragment
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.android.activity.ActivityContext
import com.moriatsushi.koject.component.Component

/**
 * Mark it as a type to inject into [Fragment].
 *
 * Additional available types:
 * * [androidx.fragment.app.Fragment]
 * * [androidx.fragment.app.FragmentActivity]
 * * [androidx.activity.ComponentActivity]
 * * [android.app.Activity]
 * * [android.content.Context] with @[ActivityContext]
 * * [kotlinx.coroutines.CoroutineScope] with @[FragmentCoroutineScope]
 * * [kotlinx.coroutines.CoroutineScope] with @[FragmentViewCoroutineScope]
 *
 * example:
 * ```
 * @Provides
 * @FragmentComponent
 * class ForFragmentClass(
 *     private val fragment: Fragment,
 *     private val activity: FragmentActivity,
 *     @ActivityContext
 *     private val context: Context,
 *     @FragmentCoroutineScope
 *     private val scope: CoroutineScope*
 * )
 * ```
 */
@OptIn(ExperimentalKojectApi::class)
@Component
@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
annotation class FragmentComponent
