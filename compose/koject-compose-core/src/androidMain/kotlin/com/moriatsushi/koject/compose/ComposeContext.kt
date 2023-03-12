package com.moriatsushi.koject.compose

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.moriatsushi.koject.Qualifier

/**
 * Get the [Context] of [LocalContext]
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
annotation class ComposeContext
