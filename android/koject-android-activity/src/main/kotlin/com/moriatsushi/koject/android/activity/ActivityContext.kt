package com.moriatsushi.koject.android.activity

import android.app.Activity
import android.app.Application
import android.content.Context
import com.moriatsushi.koject.Qualifier

/**
 * Get the [Activity]'s [Context].
 *
 * Without this annotation, [Application] Context will be injected.
 * ```
 * @Provides
 * @ActivityComponent
 * class ForActivity(
 *     val applicationContext: Context,
 *     @ActivityContext
 *     val activityContext: Context,
 * )
 * ```
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
annotation class ActivityContext
