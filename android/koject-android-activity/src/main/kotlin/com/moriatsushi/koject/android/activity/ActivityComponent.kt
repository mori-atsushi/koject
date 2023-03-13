package com.moriatsushi.koject.android.activity

import androidx.activity.ComponentActivity
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.component.Component

/**
 * Mark it as a type to inject into [ComponentActivity].
 *
 * Additional available types:
 * * [androidx.activity.ComponentActivity]
 * * [android.app.Activity]
 * * [android.content.Context] with @[ActivityContext]
 * * [kotlinx.coroutines.CoroutineScope] with @[ActivityCoroutineScope]
 *
 * example:
 * ```
 * @Provides
 * @ActivityComponent
 * class ForActivityClass(
 *     private val activity: ComponentActivity,
 *     @ActivityContext
 *     private val context: Context,
 *     @ActivityCoroutineScope
 *     private val scope: CoroutineScope
 * )
 * ```
 */
@OptIn(ExperimentalKojectApi::class)
@Component
@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
annotation class ActivityComponent
