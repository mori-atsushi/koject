package com.moriatsushi.koject.android.activity

import android.app.Activity
import androidx.lifecycle.lifecycleScope
import com.moriatsushi.koject.Qualifier
import kotlinx.coroutines.CoroutineScope

/**
 * Get a [Activity]'s [CoroutineScope] that the same as [lifecycleScope].
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
annotation class ActivityCoroutineScope
