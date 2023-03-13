package com.moriatsushi.koject.android.activity

import android.app.Activity
import androidx.activity.ComponentActivity
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.component.Component

/**
 * Mark it as a type to inject into [ComponentActivity].
 *
 * By using with @[Provides], you can inject [ComponentActivity]
 * or [Activity].
 * ```
 * @Provides
 * @ActivityComponent
 * class ForActivityClass(
 *     private val activity: ComponentActivity
 * )
 * ```
 */
@OptIn(ExperimentalKojectApi::class)
@Component
@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
annotation class ActivityComponent
